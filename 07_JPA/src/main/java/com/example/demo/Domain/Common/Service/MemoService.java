package com.example.demo.Domain.Common.Service;

import com.example.demo.Domain.Common.Dao.MemoDao;
import com.example.demo.Domain.Common.Dto.MemoDto;
import com.example.demo.Domain.Common.Dto.PageDto;
import com.example.demo.Domain.Common.Entity.Memo;
import com.example.demo.Domain.Common.Repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class MemoService {
    @Autowired
    private MemoDao memoDao;

    @Autowired
    private MemoRepository memoRepository;


    public boolean memoRegistration(MemoDto dto) throws Exception {
        int result = memoDao.insert(dto);
        return result>0;
    }

    public Long memoRegistration2(MemoDto dto) throws Exception {
        //dto -> entity
        Memo memo = Memo.builder()
                    .id(null)
                    .text(dto.getText())
                    .writer(dto.getWriter())
                    .createAt(LocalDateTime.now())
                    .build();
        memoRepository.save(memo);
        return memo.getId();
    }

    // MemoRepository에서 함수명명법으로 처리한 Page<Memo>
    @Transactional(rollbackFor=Exception.class)
    public Page<Memo> listMemo(PageDto pageDto) throws SQLException {
        // PageBlock : 15
        // 1 - 15개의 페이지 번호가 나와있는 목록 >> 버튼을 누르면 16 - 30의 페이지 번호 나옴

        // pageNo null 체크
        int pageNo = 0;
        int amount = 10; // 게시물 건수 (한페이지당 보여질)
        if(pageDto.getPageNo()!=null)
            pageNo = pageDto.getPageNo(); // null이 아니면 넣어주고
        if(pageDto.getAmount()!=null)
            amount = pageDto.getAmount();
        // 내림차순으로 정렬을 한 다음에 amount -> pageNo
        Pageable pageable = PageRequest.of(pageNo, amount, Sort.by("id").descending()); // pageDto에서 받은 pageNo, amount , 내림차순으로
        Page<Memo> page = memoRepository.findAll(pageable); // 반환형 Page<Memo>

        // 페이지 메타 확인
        System.out.println("현재 페이지 번호 : " + page.getNumber());
        System.out.println("한 페이지에 표시할 건수 : " + page.getSize());
        System.out.println("총 게시물 개수 : " + page.getTotalElements());
        System.out.println("총 페이지 개수 : " + page.getTotalPages());
        System.out.println("첫번째 페이지인지 여부 :" + page.isFirst());
        System.out.println("다음 페이지가 있는지 여부 : " + page.hasNext());
        System.out.println("이전 페이지가 있는지 여부 : " + page.hasPrevious());

        return page;
    }


}
