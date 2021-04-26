package com.saltlux.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.saltlux.mysite.security.Auth;
import com.saltlux.mysite.security.AuthUser;
import com.saltlux.mysite.service.BoardService;
import com.saltlux.mysite.vo.BoardVo;
import com.saltlux.mysite.vo.PagerVo;
import com.saltlux.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@Auth
	private void temp() {};
	
	@RequestMapping("")  //  초기화면
	public String index(HttpServletRequest request,Model model) {
		int totalCount = 0;
		
		int nowPage = 1;
		List<BoardVo> list = null;
		if (request.getParameter("p") != null) {
			nowPage = Integer.parseInt(request.getParameter("p"));
		}
		totalCount = boardService.getCount();
		PagerVo pagerVo = new PagerVo(totalCount, 5, nowPage);
		list = boardService.findAll(pagerVo);

		model.addAttribute("list", list);
		model.addAttribute("pagerVo", pagerVo);
		
		HttpSession session= request.getSession();
		if (session == null) {
			return "board/index";
		}
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "board/index";
		}
//		System.out.println("authuser :::: "+authUser);
	////////////	
		return "board/index";  //board/index.jsp
	}
	

	@Auth
	@RequestMapping("writeform")
	public String writefrom() {
		return "board/write";
	}
	@Auth
	@RequestMapping("write")
	public String write(@AuthUser UserVo authUser,HttpServletRequest request) {

		String referer = request.getHeader("Referer");
		System.out.println("이전의 경로 : " + referer);
		Long g_no = 0L;
		Long o_no = 1L;
		Long depth = 0L;// 여기까지 신규등록일 경우를 대비한 초기화 상태

		// writeform이 문자열의 마지막이면 신규 등록
		if (referer.substring(referer.length() - 9, referer.length()).equals("writeform")) {// 신규등록
			g_no = boardService.getMaxGno()+ 1; // 자동으로 Group No를 증가한다

		} else { // 답글작성
			BoardVo vo = new BoardVo();
			Long boardNo = Long.parseLong(request.getParameter("reply"));// 답을 달 원글
			vo = boardService.findByNo(boardNo);// 답을 달 글의 정보 요청

			// 원글의 정보
			g_no = vo.getG_no();
			o_no = vo.getO_no() + 1L;
			depth = vo.getDepth() + 1L;

			boardService.updateOrder(g_no, o_no); // 추가하기 전에 원글의 뒷부분에 orderno를 늘려준다
		}

		
		
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setAuthor(authUser.getName());
		vo.setContent(content);
		vo.setG_no(g_no);
		vo.setO_no(o_no);
		vo.setDepth(depth);
		vo.setUser_no(authUser.getNo());

		boardService.insert(vo);
		
		return "redirect:/board";
	}
	
	@RequestMapping("view")
	public String view(HttpServletRequest request,Model model) {
		Long no = Long.parseLong(request.getParameter("no"));
		BoardVo vo = boardService.findByNo(no);
		boardService.updateView(no);
		model.addAttribute("board", vo);

		return "board/view";
	}
	
	@RequestMapping("reply")
	public String reply(HttpServletRequest request) {
		String boardNo = request.getParameter("boardNo");
		// view에서 hidden으로 받아 오기
		request.setAttribute("boardNo", boardNo); // write.jsp로 넘겨준다
		return "board/write";
	}
		
	@RequestMapping("modifyform")
	public String modifyform(@AuthUser UserVo authUser, HttpServletRequest request, Model model) {
		String boardNo = request.getParameter("no");// 수정할 글의 번호
		System.out.println("수정할 글의 번호 : " + boardNo);
		BoardVo vo = boardService.findByNo(Long.parseLong(boardNo));

		if (!authUser.getNo().equals(vo.getUser_no())) { // 글의 작성자와 세션의 유저와 비교
			System.out.println("인증안된 접근");
			return "/board";
		}
		model.addAttribute("board", vo); // vo를 넘겨줌

		return "board/modify";
	}
	
	
	@RequestMapping("modify")
	public String modify(HttpServletRequest request) {
		Long no = Long.parseLong(request.getParameter("no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		BoardVo vo = new BoardVo();
		vo.setNo(no);
		vo.setTitle(title);
		vo.setContent(content);
		// System.out.println(vo); //디버그용
		boardService.update(vo);
		
		return "redirect:/board";
	}
	@RequestMapping("delete")
	public String delete(@AuthUser UserVo authUser, HttpServletRequest request) {
		Long delNo = Long.parseLong(request.getParameter("no"));
		BoardVo vo = new BoardVo();
		vo.setNo(delNo);
		vo.setUser_no(authUser.getNo());

		boardService.delete(vo);
		return "redirect:/board";
	}
	
}
	
	
		
	

