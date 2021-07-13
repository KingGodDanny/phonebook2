package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc")
public class PhoneController extends HttpServlet {
	
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("컨트롤러"); //잘 실행하는지 확인
		
		//파라미터 Action 값을 읽어온다.
		String action = request.getParameter("action");
		System.out.println(action);
		
		if("list".equals(action)) {
			//리스트업무
			System.out.println("[리스트]");
			
			//리스트
			PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> personList = phoneDao.getPersonList();
			
			System.out.println("==========================Controller================");
			System.out.println(personList);
			
			
			//데이터 넣기 -- Request 어트리뷰트(속성)에 데이터를 넣어준다.
			request.setAttribute("pList", personList);  //pList라는 속성의 이름으로 Request에 넣은것.
			
			
//			request.setAttribute("age", 30); 
//			request.setAttribute("name", "대니조");
			
			
			//html 작업 ---> jsp에게 시킨다  ==> forward 한다 
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/list.jsp");
			rd.forward(request, response);    //윗줄과 이줄은 한세트!
			
		} else if("wForm".equals(action)) {
			System.out.println("[글쓰기폼]");
			
			//writeForm.jsp 포워드   -->데이터X    셋어트리뷰트 할필요없음!
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/writeForm.jsp");
			rd.forward(request, response);
			
		} else if("insert".equals(action)) {
			System.out.println("[저장]");
			
			//dao -- > 저장
			//파라미터를 꺼낸다(name , hp , company)
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			//vo로 묶어준다
			PersonVo personVo = new PersonVo(name, hp, company);
			System.out.println(personVo);
			
			
			//dao personInsert()에 묶은값 넣어주기
			PhoneDao phoneDao = new PhoneDao();	  
			int count = phoneDao.personInsert(personVo);
			
			
			//리다이렉트
			response.sendRedirect("/phonebook2/pbc?action=list");
			
		} else if("delete".equals(action)) {
			System.out.println("[삭제]");
			
			//list.jsp에서 넘어온값 파라미터에 담기
			int personId = Integer.parseInt(request.getParameter("personId"));
			
			//폰다오 열어주고 딜리트메소드에 담기
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personDelete(personId);
			
			//리다이렉트
			response.sendRedirect("/phonebook2/pbc?action=list");
			
		} else if("uForm".equals(action)) {
			System.out.println("[수정]");
			
			//파라미터 꺼내기
			int personId = Integer.parseInt(request.getParameter("personId"));
			
			//폰다오 열어서 사람한명 꺼내오기
			PhoneDao phoneDao = new PhoneDao();
			PersonVo personVo = phoneDao.getPerson(personId);
			
			//데이터 넣기 -- Request 어트리뷰트(속성)에 데이터를 넣어준다.
			request.setAttribute("personList", personVo);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/updateForm.jsp");
			rd.forward(request, response);
			
		} else if("update".equals(action)) {
			System.out.println("[수정넘어간다]");
			
			//파라미터 담아주기
			int personId = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			//폰다오 공간 열어주고
			PhoneDao phoneDao = new PhoneDao();
			
			//받아온 파라미터 생성자에 넣어주고 업데이트 메소드로 보내주기
			PersonVo personVo = new PersonVo(personId, name, hp, company);
			phoneDao.personUpdate(personVo);
			
			
			//리다이렉트 -- 수정된 후 list화면 보여주기
			response.sendRedirect("/phonebook2/pbc?action=list");
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
