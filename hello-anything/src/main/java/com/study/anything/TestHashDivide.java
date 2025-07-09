package com.study.anything;

import com.study.anything.model.WeightDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestHashDivide {
    public static void main(String[] args) {
        List<WeightDto> skuDtoList = new ArrayList<>();
        skuDtoList.add(new WeightDto("A", 80));
        skuDtoList.add(new WeightDto("B", 10));
        skuDtoList.add(new WeightDto("C", 10));
        List<WeightDto> skuDtoWeightList = new ArrayList<>(skuDtoList.size() * 10); // 预估容量，避免多次扩容

        for (WeightDto skuDto : skuDtoList) {
            int weight = skuDto.getWeight();
            for (int i = 0; i < weight; i++) {
                skuDtoWeightList.add(skuDto);
            }
        }

        List<String> A = new ArrayList<>();
        List<String> B = new ArrayList<>();
        List<String> C = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            String pin = UUID.randomUUID().toString();

            int index =  skuDtoWeightList.size() -1 & Math.abs(pin.hashCode());
            //skuDtoWeightList.size()-1 & Math.abs(pin.hashCode()) ;
//            int index =  Math.abs(pin.hashCode()) % skuDtoWeightList.size();
            if(index >= skuDtoWeightList.size()){
                index--;
            }
            WeightDto selectedSkuDto = skuDtoWeightList.get(index);
            if("A".equals(selectedSkuDto.getName())){
                A.add(pin);
            }else if("B".equals(selectedSkuDto.getName())){
                B.add(pin);
            }else if("C".equals(selectedSkuDto.getName())){
                C.add(pin);
            }
        }
        System.out.println("A:" + A.size());
        System.out.println("B:" + B.size());
        System.out.println("C:" + C.size());
    }
}
