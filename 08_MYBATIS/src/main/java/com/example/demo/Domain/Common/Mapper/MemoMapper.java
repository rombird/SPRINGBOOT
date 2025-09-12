package com.example.demo.Domain.Common.Mapper;

import com.example.demo.Domain.Common.Dto.MemoDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemoMapper {

    // JPA JPQL과 비슷
    // id 에 AI 체크 후 selectkey 애노테이션 추가 - id에 있는 컬럼을 가져올 것, 반환되는 타입 long
    // SELECT max(id) FROM testdb.tbl_memo;
    @SelectKey(statement = "SELECT max(id) FROM testdb.tbl_memo", keyProperty = "id" , before = false, resultType = Long.class) // insert하기 전에 할 건지 설정
    @Insert("insert into tbl_memo values(#{id},#{text},#{writer},#{createAt})") // MemoDto에서 속성값 순서대로 입력해주기
    public int insert(MemoDto memoDto); // 각각의 항목은 memoDto와 동일

    @Update("update tbl_memo set text=#{text}, writer=#{writer} where id=#{id}") // 특정한 id의 text, writer 값 수정
    public int update(MemoDto memoDto);

    @Delete("delete from tbl_memo where id=#{id}") // 아래 컬럼명 id와 동일하게 설정해줘야함
    public int delete(@Param("id") Long memoId); // (Long id) 대신에 @Param을 사용하면 (@Param("id") Long memoId) 이름 똑같이 안 맞춰줘도 알아서 읽음

    @Select("select * from tbl_memo")
    public List<MemoDto> selectAll();

    @Select("select * from tbl_memo where ${type} like concat('%', #{keyword}, '%')")
    public List<MemoDto> selectAllContains(String type, String keyword); // 포함되어있는 열 조회

//    @Results(id="MemoResultMap", value={
//            @Result(property = "text", column = "text"), // result에 들어가는 속성명
//            @Result(property = "writer", column="writer")
//    }) // 어떤 항목을 가져올지 value에서 선정(Join 사용 or 기타 등등 특정상황에서 dto단위로 못가져올 수 있음)
    @Select("select text, wirter from tbl_memo") // select 를 하면 result의 반환형태로 반환
    public List<Map<String, Object>> selectAllWithResultMap(); // resultMap 애노테이션 사용하면 원하는 컬럼의값을 뽑아 올 수 있음
    // Results의 반환형이 <Map<String, Object>> 형으로 반환

    // Result 사용시 class 새로 만들어야함
//     @Results(id="MemoResultMap", value={
//            @Result(property = "text", column = "text"), // result에 들어가는 속성명
//            @Result(property = "writer", column="writer")
//    }) // 어떤 항목을 가져올지 value에서 선정(Join 사용 or 기타 등등 특정상황에서 dto단위로 못가져올 수 있음)
//    @Select("select * from tbl_memo") // select 를 하면 result의 반환형태로 반환
//    public List<MemoDto> selectAllWithResultMap(); // resultMap 애노테이션 사용하면 원하는 컬럼의값을 뽑아 올 수 있음
    // Results의 반환형이 <Map<String, Object>> 형으로 반환

    // XML을 사용하면 동적 SQL 을 사용 가능 : SQL 사이사이에 분기처리를 해서 다양한 상황에 맞는 쿼리를 나눌 수 있도록 사용가능
    // MemoMapper에 있는 코드 그대로 xml로 넘김 - MemoMapper와 resource -> Mapper 폴더 -> MemoMapper.xml 연결됨

    // XML 방식 : MemoMapper.xml에서 MemoMapper의 insertXML과 연결
    public int insertXML(MemoDto memoDto);
    public int updateXML(MemoDto memoDto);
    public int deleteXML(@Param("id") Long memoId);
}
