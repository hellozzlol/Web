package co.edu.member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.DAO;

public class MemberDAO extends DAO {

	// 전체조회.
	public List<membervo> memberList() {
		getConnect();
		List<membervo> list = new ArrayList<>();
		try {

			psmt = conn.prepareStatement("select * from member order by memb_no");
			rs = psmt.executeQuery();// 조회 :executeQuery(),추가 수정 삭제 : executeUpdate
			while (rs.next()) {// 가지고온 데이터가 있다 판단하고 트루로인식해 데이터 한건을 싹 가져옴
				membervo mem = new membervo();
				mem.setMembno(rs.getInt("memb_no"));
				mem.setMembname(rs.getString("memb_name"));
				mem.setMembphone(rs.getString("memb_phone"));
				mem.setMembbirth(rs.getString("memb_birth"));
				mem.setMembaddr(rs.getString("memb_addr"));
				mem.setMembImage(rs.getNString("memb_image"));

				list.add(mem);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return list;

	}

	// 입력.(MemberVo)

	public membervo insertMember(membervo vo) {
		getConnect();

		try {
			// 시퀀스
			int nextSeq = 0;
			String seqSql = "select memb_seq.nextval from dual";
			psmt = conn.prepareStatement(seqSql);
			rs = psmt.executeQuery();
			if (rs.next()) {
				nextSeq = rs.getInt(1); // 회원번호.
			}
			// 입력처리.
			psmt = conn.prepareStatement(
					"insert into member(memb_no,memb_name,memb_phone,memb_addr,memb_birth" 
			+ ",memb_image) values(?,?,?,?,?,?)");
			psmt.setInt(1, nextSeq);
			psmt.setString(2, vo.getMembname());
			psmt.setString(3, vo.getMembphone());
			psmt.setString(4, vo.getMembaddr());
			psmt.setString(5, vo.getMembbirth());
			psmt.setString(6, vo.getMembImage());
			vo.setMembno(nextSeq);
			int r = psmt.executeUpdate();

			System.out.println(r + "건 입력.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return vo;
	}

	// 수정.(MemberVo)
	public boolean updateMember(membervo vo) {
		getConnect();
		String spl = "update member set" + " memb_name=?, "// "update meber setmemb
				+ " memb_addr=?, "//
				+ " memb_phone=?, "//
				+ " memb_birth=?, "//
				+ " memb_image=? "//
				+ "where memb_no = ? ";
		try {
			psmt = conn.prepareStatement(spl);
			psmt.setString(1, vo.getMembname());
			psmt.setString(2, vo.getMembphone());
			psmt.setString(3, vo.getMembaddr());
			psmt.setString(4, vo.getMembbirth());
			psmt.setString(5, vo.getMembImage());
			psmt.setInt(6, vo.getMembno());

			int r = psmt.executeUpdate();// 수정된 건수를 반환.
			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return false;
	}

	// 삭제.(memb_no)
	public boolean deleteMember(int membno) {
		getConnect();
		String spl = "delete from member where memb_no=?";
		try {
			psmt = conn.prepareStatement(spl);
			psmt.setInt(1, membno);
			int r = psmt.executeUpdate();// 삭제
			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return false;
	}

	// 조건조회.(memb_no)
	public membervo searchMember(int membno) {
		getConnect();
		membervo mem = null;//초기 값
	
		try {

			psmt = conn.prepareStatement("select * from member where memb_no=?");
			psmt.setInt(1, membno);
			rs = psmt.executeQuery();// 조회 :executeQuery(),추가 수정 삭제 : executeUpdate
			if (rs.next()) {// 가지고온 데이터가 있다 판단하고 트루로인식해 데이터 한건을 싹 가져옴
				mem = new membervo();
				mem.setMembno(rs.getInt("memb_no"));
				mem.setMembname(rs.getString("memb_name"));
				mem.setMembphone(rs.getString("memb_phone"));
				mem.setMembaddr(rs.getString("memb_addr"));
				mem.setMembbirth(rs.getNString("memb_birth"));
				mem.setMembImage(rs.getNString("memb_image"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return mem;
	}

}
