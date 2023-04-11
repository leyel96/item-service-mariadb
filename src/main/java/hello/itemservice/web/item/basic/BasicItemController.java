package hello.itemservice.web.item.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import hello.itemservice.domain.item.service.itemservice;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    @Autowired
    private itemservice itemService;

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemService.searchAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemService.searchItem(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model) {
        System.out.println("추가");
        String result = itemService.insertMember(item);
        System.out.println(result);
        model.addAttribute("item", item); //자동 추가, 생략 가능
        return "basic/item";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemService.searchItem(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
//        if(itemService.searchItem(itemId).isEmpty()){
//            return "/redirect:/basic/items";
//        } else {
            itemService.updateMember(itemId, item);
            return "redirect:/basic/items/{itemId}";
//        }
    }

//    /**
//     * 테스트용 데이터 추가
//     */
//    @PostConstruct
//    public void init() {
//        itemRepository.save(new Item("testA", 10000, 10));
//        itemRepository.save(new Item("testB", 20000, 20));
//    }
}

