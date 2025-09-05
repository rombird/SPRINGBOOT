package com.example.demo.Domain.Common.Service;

import com.example.demo.Domain.Common.Dao.MemoDao;
import com.example.demo.Domain.Common.Dto.MemoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// memo service에서는 알아보기 쉽게 함수명 설정


// MemoService를 -> MemoDao와 연결
@Service // 빈으로 들어가게됨
public class MemoService {
    @Autowired
    private MemoDao memoDao;

    // controller와 persistancelayer 에서는 할 것이 정해져있지만 service 영역은 할게 많음(service를 얼마나 잘 만드느냐에 따라 프로젝트의 질이 달라짐)***
    // 등록되었는지 아닌지 보기 위한 함수이므로 boolean 형으로 지정
    public boolean memoRegistration(MemoDto dto) throws Exception{
        int result = memoDao.insert(dto); //지금은 당장 할게 없기 때문에 -> dao에서 문제가 생기면 예외로 던져지고...
        return result>0;
    }

}
