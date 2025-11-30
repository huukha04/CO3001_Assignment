/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.user.dto.req;

/**
 *
 * @author khahu
 */
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
public class CreateSlotRequest {
    private Date start;      // thời gian bắt đầu
    private Date end;        // thời gian kết thúc
    private String location; // địa điểm
}