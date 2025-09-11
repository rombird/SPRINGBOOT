package com.example.demo.Domain.Common.Repository;

import com.example.demo.Domain.Common.Entity.Memo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemoRepositoryTest {

    @Autowired
    private MemoRepository memoRepository;

    @Test
    public void t1(){
        Memo memo = new Memo(null,"내용1","test@test.com", LocalDateTime.now());
        memoRepository.save(memo);

        System.out.println("ID : " + memo.getId());
    }

    @Test
    public void t2 (){
        Memo memo = new Memo(1L,"수정된내용","test@test.com", LocalDateTime.now());
        memoRepository.save(memo);
        System.out.println("ID : " + memo.getId());
    }
    @Test
    public void t3(){
        memoRepository.deleteById(2L);
    }
    @Test
    public void t4(){
       Optional<Memo> memoOptional =   memoRepository.findById(10L);
       if(memoOptional.isPresent()){
           Memo memo = memoOptional.get();
           System.out.println(memo);
       }
    }
    @Test
    public void t5(){
        List<Memo> list = memoRepository.findAll();
        list.forEach(System.out::println);
        //(el)->{System.out.println(el);}
        //el->System.out.println(el);
        //System.out::println;
    }

    // JPA
    @BeforeEach // 테스트하기전에 실행, 사전에 할 것을 선정가능 - 테스트를 실행하면 무조건 실행 같이 됨
    public void post1000(){
        if(memoRepository.count()==0){
            // 하나도 값이 없으면 -> 1000건 정도 값을 넣겠다
            for(int i=0;i<1000;i++){
                memoRepository.save(new Memo(null, "TEXT-"+i, "WRITER-"+i,LocalDateTime.now())); // id는 autoincrement라 null로 지정
            }
        }
    }
    // 총데이터 1000건
    // 한 화면 10건 -> 1000/10 ->100page 한페이지에 표시할 건수를 나눠서 페이지 단위로 처리하는 일이 먼저
    // 게시글에서 한화면에 보여질 건수
    @Test
    public void t6(){
        System.out.println(memoRepository.count());
        Pageable pageable = PageRequest.of(0, 10); // pageable을 가져와 page 요청(0번째 페이지, 한 페이지당 건수)
        Page<Memo> page = memoRepository.findAll(pageable); // 반환형 Page<Memo>

        // 페이지 메타 확인
        System.out.println("현재 페이지 번호 : " + page.getNumber());
        System.out.println("한 페이지에 표시할 건수 : " + page.getSize());
        System.out.println("총 게시물 개수 : " + page.getTotalElements());
        System.out.println("총 페이지 개수 : " + page.getTotalPages());
        System.out.println("첫번째 페이지인지 여부 :" + page.isFirst());
        System.out.println("다음 페이지가 있는지 여부 : " + page.hasNext());
        System.out.println("이전 페이지가 있는지 여부 : " + page.hasPrevious());

        // 실제 페이지 꺼내는 작업
        List<Memo> list = page.getContent();
        list.forEach(System.out::println);
        System.out.println("---");
        // 다음 페이지 요청
        Page<Memo> nextPage = memoRepository.findAll(page.nextPageable());

    }

}
