package com.ye.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description: TODO
 * @author: scott
 * @date: 2021年07月04日 23:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user")
public class User implements Serializable {
    @TableField(value = "id")
    private int id;

    @TableField(value = "name")
    private String Name;

    @TableField(value = "pwd")
    private String pwd;

    @TableField(value = "perms")
    private String perms;

    @TableField(value = "roles")
    private String roles;
}
