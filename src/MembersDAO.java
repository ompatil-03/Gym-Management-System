import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembersDAO {

    public void addMember(Member member) throws SQLException {
        String sql = "INSERT INTO Members (name, age, contact, membership_type, start_date, expiry_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, member.getName());
            stmt.setInt(2, member.getAge());
            stmt.setString(3, member.getContact());
            stmt.setString(4, member.getMembershipType());
            stmt.setDate(5, new java.sql.Date(member.getStartDate().getTime()));
            stmt.setDate(6, new java.sql.Date(member.getExpiryDate().getTime()));
            stmt.executeUpdate();
        }
    }

    public List<Member> getAllMembers() throws SQLException {
        String sql = "SELECT * FROM Members";
        List<Member> members = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Member member = new Member();
                member.setId(rs.getInt("id"));
                member.setName(rs.getString("name"));
                member.setAge(rs.getInt("age"));
                member.setContact(rs.getString("contact"));
                member.setMembershipType(rs.getString("membership_type"));
                member.setStartDate(rs.getDate("start_date"));
                member.setExpiryDate(rs.getDate("expiry_date"));
                members.add(member);
            }
        }
        return members;
    }

    public void updateMember(Member member) throws SQLException {
        String sql = "UPDATE Members SET name=?, age=?, contact=?, membership_type=?, start_date=?, expiry_date=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, member.getName());
            stmt.setInt(2, member.getAge());
            stmt.setString(3, member.getContact());
            stmt.setString(4, member.getMembershipType());
            stmt.setDate(5, new java.sql.Date(member.getStartDate().getTime()));
            stmt.setDate(6, new java.sql.Date(member.getExpiryDate().getTime()));
            stmt.setInt(7, member.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteMember(int memberId) throws SQLException {
        String sql = "DELETE FROM Members WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, memberId);
            stmt.executeUpdate();
        }
    }

    public Member searchMemberByName(String name) throws SQLException {
        String sql = "SELECT * FROM Members WHERE name=?";
        Member member = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    member = new Member();
                    member.setId(rs.getInt("id"));
                    member.setName(rs.getString("name"));
                    member.setAge(rs.getInt("age"));
                    member.setContact(rs.getString("contact"));
                    member.setMembershipType(rs.getString("membership_type"));
                    member.setStartDate(rs.getDate("start_date"));
                    member.setExpiryDate(rs.getDate("expiry_date"));
                }
            }
        }
        return member;
    }
}
