package one.jp.ssrpg;

import java.util.ArrayList;

import ship.modules.CargoBayModule;

public class CargoBay {

    ArrayList<Goods> goods;
    CargoBayModule module;

    public CargoBay(ArrayList<Goods> goodsArrayList) {
        goods = goodsArrayList;
    }

}
