package com.example.appmusicc.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChuDe implements Serializable {

@SerializedName("idChuDe")
@Expose
private String idChuDe;
@SerializedName("tenChuDe")
@Expose
private String tenChuDe;
@SerializedName("hinhchude")
@Expose
private String hinhchude;

public String getIdChuDe() {
return idChuDe;
}

public void setIdChuDe(String idChuDe) {
this.idChuDe = idChuDe;
}

public String getTenChuDe() {
return tenChuDe;
}

public void setTenChuDe(String tenChuDe) {
this.tenChuDe = tenChuDe;
}

public String getHinhchude() {
return hinhchude;
}

public void setHinhchude(String hinhchude) {
this.hinhchude = hinhchude;
}

}