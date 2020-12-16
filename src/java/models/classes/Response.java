/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.classes;

/**
 *
 * 
 */
public class Response {
    private boolean ok;
    private int rows_number;
    private String data;

    public Response(boolean ok, int rows_number, String data) {
        this.ok = ok;
        this.rows_number = rows_number;
        this.data = data;
    }

    public boolean isOk() {
        return ok;
    }

    public int getRows_number() {
        return rows_number;
    }

    public String getData() {
        return data;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public void setRows_number(int rows_number) {
        this.rows_number = rows_number;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    
}
