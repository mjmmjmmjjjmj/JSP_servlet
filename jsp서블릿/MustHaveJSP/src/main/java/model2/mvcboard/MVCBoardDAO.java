package model2.mvcboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;

public class MVCBoardDAO extends JDBConnect { //커넥션 풀 상송
	public MVCBoardDAO() {
		super();
	}
	
	//검색 조건에 맞는 게시물의 개수를 반환
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		//쿼리문 준비
		String query = "select count(*) from mvcboard";
		//검색 조건이 있다고 where절로 추가
		if (map.get("searchWord") != null) {
			query += " where " + map.get("searchField") + " "
					+ " like '%" + map.get("searchWord") + "%'";
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		}
		catch(Exception e) {
			System.out.println("게시물 카운트 중 예외 발생");
			e.printStackTrace();
		}
		
		return totalCount; //게시물 개수를 서블릿으로 변환	
	}
	
	//검색 조건에 맞는 게시물 목록을 반환(페이징 기능 지원)
	public List<MVCBoardDTO> selectListPage(Map<String, Object> map) {
		List<MVCBoardDTO> board = new Vector<MVCBoardDTO>();
		//쿼리문 준비
		String query = "select * from mvcboard ";
		
		//검색조건이 있다면 where절로 추가
		if (map.get("searchWord") != null) {
			query += " where " + map.get("searchField")
					+ "	like '%" + map.get("searchWord") + "%'";
		}
		query += " order by idx desc limit ?,? "; //게시물 구간은 인파라미터로
		
		try {
			psmt = con.prepareStatement(query); //동적 쿼리문 실행
			psmt.setInt(1, (int)map.get("start")); // 인파라미터 설정
			psmt.setInt(2, (int)map.get("pageSize")); //쿼리문 실행		
			rs = psmt.executeQuery();
			
			//반환된 게시물 목록은 List 컬렉션에 추가
			while(rs.next()) {
				MVCBoardDTO dto = new MVCBoardDTO();
				
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
				
				board.add(dto);
			}
		}
		catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return board; //목록 반환
	}
	
	// 게시글 데이터 받아 DB에 추가(*파일 업로드 지원)
	public int insertWrite(MVCBoardDTO dto) {
		int result=0;
		try {
			//idx가 자동 증가 옵션(auto_increment)으로 되어 있어 입력필요x
			String query = "insert into mvcboard ( "
						 + " name, title, content, ofile, sfile, pass) "
						 + " values ( "
						 + " ?, ?, ?, ?, ?, ?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2,  dto.getTitle());
			psmt.setString(3,  dto.getContent());
			psmt.setString(4,  dto.getOfile());
			psmt.setString(5,  dto.getSfile());
			psmt.setString(6,  dto.getPass());
			
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	//주어진 일련번호에 해당하는 게시물을 DTO에 담아 반환
	public MVCBoardDTO selectView(String idx) {
		MVCBoardDTO dto = new MVCBoardDTO(); //DTO 객체 생성
		String query = "select * from mvcboard where idx=?"; //쿼리문 템플릿 준비
		try {
			psmt = con.prepareStatement(query); //쿼리문 준비
			psmt.setString(1,  idx); //인파라미터 설정
			rs = psmt.executeQuery(); //쿼리문 실행
			
			if (rs.next()) { //결과를 DTO 객체에 저장
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
				
			}
		}
		catch (Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		return dto; //결과 반환
	}
	
	//주어진 일련번호에 해당하는 게시물의 조회수를 1 증가시킴
	public void updateVisitCount(String idx) {
		String query = "update mvcboard set "
					 + " visitcount = visitcount + 1 "
					 + " where idx=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			psmt.executeQuery();
		}
		catch (Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	//다운로드 횟수를 1증가시킴
	public void downCountPlus(String idx) {
		String sql = "update mvcboard set "
					+ " downcount = downcount+1 "
					+ " where idx=? ";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idx);
			psmt.executeUpdate();
		}
		catch(Exception e) {
			
		}
	}
	
	//입력 비밀번호와 지정한 일련번호 게시물 비밀번호랑 일치 확인
	public boolean confirmPassword(String pass, String idx) {
		boolean isCorr = true;
		try {
			String sql = "select count(*) from mvcboard where pass=? and idx=?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, pass);
			psmt.setString(2,  idx);
			rs = psmt.executeQuery();
			rs.next();
			if (rs.getInt(1)==0) {
				isCorr = false;
			}
		}
		catch(Exception e) {
			isCorr = false;
			e.printStackTrace();
		}
		return isCorr;
		
	}
	//지정 일련번호 게시물 삭제
	public int deletePost(String idx) {
		int result = 0;
		try {
			String query = "delete from mvcboard where idx=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			result = psmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	//게시글 데이터 받아 DB에 저장되어 있던 내용 갱신(파일 업로드 지원)
	public int updatePost(MVCBoardDTO dto) {
		int result = 0;
		try {
//			쿼리문 템플릿 준비
			String query = "update mvcboard"
						+ " set title=?, name=?, content=?, ofile=?, sfile=?"
						+ " where idx=? and pass=? ";
			//쿼리문 준비
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getIdx());
			psmt.setString(7, dto.getPass());
			
			//query문 실행
			result = psmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("게시물 수정 중 예외 발생");;
			e.printStackTrace();
		}
		return result;
		
	}
}
