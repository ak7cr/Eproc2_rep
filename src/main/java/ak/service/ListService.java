package ak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ak.entity.ListEntity;
import ak.repository.ListRepository;

import java.util.List;

@Service
public class ListService {

    @Autowired
    private ListRepository listRepository;

    public List<ListEntity> getAllLists() {
        return listRepository.findAll();
    }
}