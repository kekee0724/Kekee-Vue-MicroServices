package org.kekee.vo;

import lombok.Data;

/**
 * @author cocoa
 */
@Data
public class User {
    private Integer uid;
    private String uname;
    private String uemail;
    private String upassword;
}
