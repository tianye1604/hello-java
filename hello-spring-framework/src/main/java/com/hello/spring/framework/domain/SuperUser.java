package com.hello.spring.framework.domain;

import com.hello.spring.framework.annotation.Super;
import lombok.Data;

@Data
@Super
public class SuperUser extends User{
    private String address;

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                '}'+ super.toString();
    }
}
