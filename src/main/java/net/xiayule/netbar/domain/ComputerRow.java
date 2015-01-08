package net.xiayule.netbar.domain;

import net.xiayule.netbar.entity.Computer;

/**
 * Created by tan on 15-1-8.
 */
public class ComputerRow {
    /**
     * 机号
     */
    private Integer id;
    /**
     * 状态，0表示空闲，1表示已上机
     */
    private String state;
    /**
     * 上机人姓名
     */
    private String username;

    /**
     * 余额
     */
    private Double balance;

    public ComputerRow() {

    }

    public ComputerRow(Integer id, String state, String username, Double balance) {
        this.id = id;
        this.state = state;
        this.username = username;
        this.balance = balance;
    }

    // set and get methods

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
