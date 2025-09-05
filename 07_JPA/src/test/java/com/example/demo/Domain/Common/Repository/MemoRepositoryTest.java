package com.example.demo.Domain.Common.Repository;

import com.example.demo.Domain.Common.Entity.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// memoDao 없이 하는 작업
@SpringBootTest
class MemoRepositoryTest {
    // 의존주입 받음
    @Autowired
    private MemoRepository memoRepository;

    @Test
    public void t1(){
        Memo memo = new Memo(null, "내용1", "test@test.com", LocalDateTime.now()); // entity 단위를 전달
        memoRepository.save(memo); // 여기서 insert는 save , entity 단위
        System.out.println("ID : " + memo.getId()); // insert를 한다음 select로 꺼내올 수 있음 -> ID : 3
    } // 계속 실행할수록 내용 추가됨(id를 자동생성했기 때문에)

    @Test
    public void t2(){
        Memo memo = new Memo(1L, "수정된 내용", "test@test.com", LocalDateTime.now()); // insert, update는 동일
        memoRepository.save(memo); // insert, update는 동일 ->text 내용 수정됨을 확인
        System.out.println("ID : " + memo.getId());
    }

    @Test
    public void t3(){
        memoRepository.deleteById(2L); // Id 2번 찾기
    }

    // ID : 3 찾기
    @Test
    public void t4(){
        Optional<Memo> memoOptional = memoRepository.findById(3L);
        if(memoOptional.isPresent()){
            // id = 3이 있으면
            Memo memo = memoOptional.get();
            System.out.println(memo); // Memo(id=3, text=내용1, writer=test@test.com, createAt=2025-09-05T12:39:31.170383)
        }
    }

    // 전체 조회
    @Test
    public void t5(){
        List<Memo> list = memoRepository.findAll(); // 리스트맵으로 받기
        list.forEach(System.out::println);
        //(el) -> {System.out.println(el);}
        // el -> System.out.println(el);
        // System.out::
    }




}