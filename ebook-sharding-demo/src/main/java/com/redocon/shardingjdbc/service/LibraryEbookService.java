package com.redocon.shardingjdbc.service;

import java.util.List;
import java.util.Map;

public interface LibraryEbookService {

    List<Map> queryByOrderId(Integer orderId, Integer pageNum, Integer sizePerPage);
    List<Map> queryByLibraryId(Integer libraryId, Integer pageNum, Integer sizePerPage);
    List<Map> queryByCustomerId(Integer customerId, Integer pageNum, Integer sizePerPage);
}
