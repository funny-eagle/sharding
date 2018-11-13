package com.redocon.shardingjdbc.service.impl;

import com.redocon.shardingjdbc.mapper.LibraryEbookMapper;
import com.redocon.shardingjdbc.service.LibraryEbookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class LibraryEbookServiceImpl implements LibraryEbookService {

    @Resource
    private LibraryEbookMapper mapper;

    @Override
    public List<Map> queryByOrderId(Integer orderId, Integer pageNum, Integer sizePerPage) {
        return mapper.selectByOrderId(orderId, pageNum, sizePerPage);
    }

    @Override
    public List<Map> queryByLibraryId(Integer libraryId, Integer pageNum, Integer sizePerPage) {
        return mapper.selectByLibraryId(libraryId, pageNum, sizePerPage);
    }

    @Override
    public List<Map> queryByCustomerId(Integer customerId, Integer pageNum, Integer sizePerPage) {
        return mapper.selectByCustomerId(customerId, pageNum, sizePerPage);
    }
}
