/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.user.service.impl;

/**
 *
 * @author khahu
 */
import com.backend.features.user.dto.req.*;
import com.backend.features.user.service.MenteeService;
import com.backend.features.course.entity.SectionRegistration;
import com.backend.features.course.repository.SectionRegistrationRepository;
import com.backend.features.feedback.entity.SessionFeedback;
import com.backend.features.feedback.repository.SessionFeedbackRepository;
import com.backend.features.schedule.entity.AvailableSlot;
import com.backend.features.schedule.entity.SlotBooking;
import com.backend.features.schedule.repository.AvailableSlotRepository;
import com.backend.features.schedule.repository.SlotBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MenteeServiceImpl implements MenteeService {

    @Autowired
    private SectionRegistrationRepository sectionRegistrationRepository;

    @Autowired
    private SessionFeedbackRepository sessionFeedbackRepository;

    @Autowired
    private AvailableSlotRepository availableSlotRepository;

    @Autowired
    private SlotBookingRepository slotBookingRepository;

    @Override
    public String registerSection(RegisterSectionRequest request) {
        try {
            SectionRegistration registration = new SectionRegistration();
            registration.setMenteeId(request.getMenteeId());
            registration.setSectionId(request.getSectionId());
            registration.setRegisterDate(new Date());
            registration.setRegisterStatus(true);
            registration.setApproved(false);

            SectionRegistration savedRegistration = sectionRegistrationRepository.save(registration);
            return "Registered section successfully. Registration Id: " + savedRegistration.getId();
        } catch (Exception e) {
            return "Failed to register section: " + e.getMessage();
        }
    }

    @Override
    public String cancelRegistration(RegisterSectionRequest request) {
        try {
            List<SectionRegistration> registrations = sectionRegistrationRepository.findAll();
            for (SectionRegistration reg : registrations) {
                if (reg.getMenteeId().equals(request.getMenteeId()) &&
                    reg.getSectionId().equals(request.getSectionId())) {
                    sectionRegistrationRepository.delete(reg);
                    return "Cancelled registration successfully. Section Id: " + request.getSectionId();
                }
            }
            return "Registration not found.";
        } catch (Exception e) {
            return "Failed to cancel registration: " + e.getMessage();
        }
    }

    @Override
    public String createFeedback(FeedbackRequest request) {
        try {
            SessionFeedback feedback = new SessionFeedback();
            feedback.setSectionId(request.getSectionId());
            feedback.setRating(request.getRating());
            feedback.setComment(request.getComment());
            feedback.setSubmitDate(new Date());

            SessionFeedback savedFeedback = sessionFeedbackRepository.save(feedback);
            return "Feedback created successfully. Feedback Id: " + savedFeedback.getFeedbackId();
        } catch (Exception e) {
            return "Failed to create feedback: " + e.getMessage();
        }
    }

    @Override
    public Object getAvailableSlots(String tutorId) {
        try {
            List<AvailableSlot> slots = availableSlotRepository.findAll();
            List<AvailableSlot> availableSlots = slots.stream()
                .filter(slot -> "FREE".equals(slot.getStatus()))
                .toList();
            return availableSlots;
        } catch (Exception e) {
            return "Failed to retrieve available slots: " + e.getMessage();
        }
    }

    @Override
    public String bookSlot(BookSlotRequest request) {
        try {
            if (request == null || request.getSlotId() == null || request.getMenteeId() == null) {
                return "Invalid request parameters.";
            }
            String slotId = request.getSlotId();
            @SuppressWarnings("null")
            Optional<AvailableSlot> slotOptional = availableSlotRepository.findById(slotId);
            if (slotOptional.isEmpty()) {
                return "Slot not found.";
            }

            AvailableSlot slot = slotOptional.get();
            if (!"FREE".equals(slot.getStatus())) {
                return "Slot is not available for booking.";
            }

            // Create slot booking
            SlotBooking booking = new SlotBooking();
            booking.setMenteeId(request.getMenteeId());
            booking.setSlotId(request.getSlotId());
            booking.setBookingDate(new Date());

            SlotBooking savedBooking = slotBookingRepository.save(booking);

            // Update slot status to Booked
            slot.setStatus("BOOKED");
            availableSlotRepository.save(slot);

            return "Slot booked successfully. Booking Id: " + savedBooking.getBookingId();
        } catch (Exception e) {
            return "Failed to book slot: " + e.getMessage();
        }
    }

    @Override
    public String cancelSlot(BookSlotRequest request) {
        try {
            if (request == null || request.getSlotId() == null || request.getMenteeId() == null) {
                return "Invalid request parameters.";
            }
            List<SlotBooking> bookings = slotBookingRepository.findAll();
            SlotBooking bookingToDelete = null;
            for (SlotBooking booking : bookings) {
                if (booking.getMenteeId().equals(request.getMenteeId()) &&
                    booking.getSlotId().equals(request.getSlotId())) {
                    bookingToDelete = booking;
                    break;
                }
            }

            if (bookingToDelete == null) {
                return "Booking not found.";
            }

            // Delete the booking
            slotBookingRepository.delete(bookingToDelete);

            // Update slot status back to Free
            String slotIdToRelease = request.getSlotId();
            if (slotIdToRelease != null) {
                Optional<AvailableSlot> slotOptional = availableSlotRepository.findById(slotIdToRelease);
                if (slotOptional.isPresent()) {
                    AvailableSlot slot = slotOptional.get();
                    slot.setStatus("FREE");
                    availableSlotRepository.save(slot);
                }
            }

            return "Slot cancelled successfully. Slot Id: " + request.getSlotId();
        } catch (Exception e) {
            return "Failed to cancel slot: " + e.getMessage();
        }
    }
}
