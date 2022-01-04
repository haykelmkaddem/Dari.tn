package com.esprit.dari.controller.furnituresController;

import com.esprit.dari.entities.furnituresEntities.Furniture;
import com.esprit.dari.entities.furnituresEntities.GiftPoints;
import com.esprit.dari.services.furnituresServices.IGiftPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/furnitures")
public class GiftPointsRestController {

    @Autowired
    IGiftPointsService iGiftPointsService;

    @GetMapping("/giftPointsTransfer/{userDariId}/{points}")
    @ResponseBody
    public Long giftPointsTransfer(@PathVariable("userDariId") Long userDariId, @PathVariable("points") int points){
        return iGiftPointsService.transferGiftPoints(userDariId, points);
    }

    @DeleteMapping("/deleteGiftPoints/{giftPointsId}")
    @ResponseBody
    public void deleteGiftPoints(@PathVariable("giftPointsId") Long giftPointsId){
        iGiftPointsService.deleteGiftPointsById(giftPointsId);
    }

    @GetMapping(value = "/getAllCGiftPoints")
    @ResponseBody
    public List<GiftPoints> findGiftPoints(){
        return iGiftPointsService.getAllGiftPoints();
    }

    @PutMapping("/modifierGiftPoints/{giftPointsId}")
    @ResponseBody
    public Long modifierGiftPoints(@PathVariable("giftPointsId") Long id,@RequestBody GiftPoints giftPoints){
        iGiftPointsService.modifiererGiftPoints(id,giftPoints);
        return giftPoints.getGiftpointsId();
    }

    @GetMapping(value = "/getGiftPointsById/{giftPointsId}")
    @ResponseBody
    public GiftPoints findGiftPointsById(@PathVariable("giftPointsId")Long id){
        return iGiftPointsService.getGiftPointsById(id);
    }
}
