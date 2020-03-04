import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.List;

public class Company {
    private int id;
    private String code;
    @SerializedName("name_full")
    private String nameFull;
    @SerializedName("name_short")
    private String nameShort;
    private String inn;
    @SerializedName("company_type")
    private CompanyType companyType;
    private String ogrn;
    @SerializedName("egrul_date")
    private LocalDate egrulDate;
    private Country country;
    @SerializedName("fio_head")
    private String fioHead;
    private String address;
    private String phone;
    @SerializedName("e_mail")
    private String eMail;
    private String www;
    @SerializedName("is_resident")
    private boolean isResident;
    private List<Security> securities;

    public List<Security> getSecurities() {
        return securities;
    }

    public String getNameShort() {
        return nameShort;
    }

    public LocalDate getEgrulDate() {
        return egrulDate;
    }
}
