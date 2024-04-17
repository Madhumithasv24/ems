package com.expeditors.ems.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse extends BaseResponseWithoutData{
        public Object data;//when you request there is no data but when you get response we need data as an object

        public BaseResponse(String status, String message, Object data) {
            super(status, message);//super helps us to use the fields in the parent class
            this.data = data;
        }

    }

