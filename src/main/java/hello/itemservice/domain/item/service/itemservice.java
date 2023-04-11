package hello.itemservice.domain.item.service;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class itemservice {

    @Autowired
    private ItemRepository itemRepository;

    private Item item;

    public List<Item> searchAll(){
        return itemRepository.findAll();
    }

    public Item searchItem(Long id){
        Optional<Item> item = itemRepository.findById(id);
        if(item.isEmpty()) {
            System.out.println("없는데?");
        }
        return item.get();
    }

    public String insertMember(Item item) {
        System.out.println("itemService.insertMember");
//        if(itemRepository.findById(item.getId()).isPresent()) {
//            return "동일한 이름이 이미 있습니다";
//        } else {
            itemRepository.save(Item.builder().itemname(item.getItemname()).price(item.getPrice()).quantity(item.getQuantity()).build());
            return "이름 : " + item.getItemname() + " 가격 : " + item.getPrice() +"수량" +item.getQuantity()+ "으로 추가 되었습니다";
//        }
    }
    public String updateMember(Long id,Item updateParam) {
        if(itemRepository.findById(id).isEmpty()) { // 값 존재여부 확인
            return "입력한 " + id + "이 존재하지 않습니다";
        } else {
            itemRepository.save(Item.builder().itemname(updateParam.getItemname()).price(updateParam.getPrice()).quantity(updateParam.getQuantity()).build());
            return "이름 : " + updateParam.getItemname() + " 가격 : " + updateParam.getPrice() +"수량" +updateParam.getQuantity()+ "으로 수정 되었습니다";
        }
    }
}
