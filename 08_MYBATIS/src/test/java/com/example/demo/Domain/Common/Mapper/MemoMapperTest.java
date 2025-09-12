package com.example.demo.Domain.Common.Mapper;

import com.example.demo.Domain.Common.Dto.MemoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MemoMapperTest {
    @Autowired
    private MemoMapper memoMapper;

    // table 내용 추가
//    @Test
//    public void post1000(){
//        for(int i=1;i<1000;i++){
//            memoMapper.insert(new MemoDto(null, "content-"+i, "writer-"+i,LocalDateTime.now(), null)); // id 자동증가라 null
//        }
//    }

    @Test
    public void t1(){
        MemoDto dto = new MemoDto(1L, "내용1", "작성자1", LocalDateTime.now(), null);
        memoMapper.insert(dto);
    }
    @Test
    public void t2(){
        MemoDto dto = new MemoDto(1L, "내용1-UPDATE", "작성자1-UPDATE", null, null);
        memoMapper.update(dto);
    }

    @Test
    public void t3(){
        memoMapper.delete(1L); // delete시 MemoMapper에서 id 로 잡아놨기 때문에 id 입력
    }

    // MYSQL에서 ID를 AI로 체크하고 실행하면 System.out.println(dto)실행시 id가 보여지지 않음
    // Mapper에 SelectKey 애노테이션 추가하고 t4 실행하면 -> System.out.println(dto)에서 id 조회가능 -> 그 다음 값을 조회
    @Test
    public void t4(){
        // id를 null로 잡으면 오류가 뜸 -> SQL에 가서 id를 AI 체크 해주고 실행시키면 자동 증가
        MemoDto dto = new MemoDto(null, "내용1", "작성자1", LocalDateTime.now(), null);
        memoMapper.insert(dto);
        System.out.println(dto);
    }

    @Test
    public void t5(){
        // selectAll : 모든 값 조회
//        List<MemoDto> list = memoMapper.selectAll();
//        list.forEach(System.out::println);
        // writer, 2 포함된 값 조회
        List<MemoDto> list = memoMapper.selectAllContains("writer", "2");
        list.forEach(System.out::println);
    }

    @Test
    public void t6(){
        List<Map<String, Object>> list =
        memoMapper.selectAllWithResultMap();
        list.forEach((map)->{
            System.out.println(map);
        });
    }

    @Test
    public void t7(){
        MemoDto dto = new MemoDto(2000L,"내용1", "작성자1", LocalDateTime.now(),null );
        memoMapper.insertXML(dto);
    }
}