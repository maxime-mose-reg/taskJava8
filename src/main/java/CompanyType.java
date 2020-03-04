import com.google.gson.annotations.SerializedName;

public class CompanyType {
    private int id;
    @SerializedName("name_short")
    private String nameShort;
    @SerializedName("name_full")
    private String nameFull;
}
