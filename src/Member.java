import java.util.Date;

public class Member {
    private int id;
    private String name;
    private int age;
    private String contact;
    private String membershipType;
    private Date startDate;
    private Date expiryDate;

    public Member(int id, String name, int age, String contact, String membershipType, Date startDate, Date expiryDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.contact = contact;
        this.membershipType = membershipType;
        this.startDate = startDate;
        this.expiryDate = expiryDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", contact='" + contact + '\'' +
                ", membershipType='" + membershipType + '\'' +
                ", startDate=" + startDate +
                ", expiryDate=" + expiryDate +
                '}';
    }
    public Member(){
        super();
    }
}
