package com.qwl.batchdemo.util;

import java.util.Random;

/**
 * @author 齐威龙
 * @date 2020/6/8 17:01
 */
public class RandomName {
    /**
     * 姓氏
     */
    private static String firstName = "齐 赵 钱 孙 李 周 吴 郑 王 冯 陈 褚 卫 蒋 沈 韩 杨 朱 秦 尤 许 何 吕 施 张 孔 曹 严 华 金 魏 陶 姜 戚 谢 邹 喻 " +
            "柏 水 窦 章 云 苏 潘 葛 奚 范 彭 郎 万俟 司马 上官 欧阳 夏侯 诸葛 闻人 东方 赫连 皇甫 尉迟 公羊";
    /**
     * 名字
     */
    private static String secondName = "一、二、三、四、威龙、碧凡、夏菡、曼香、若烟、半梦、雅绿、冰蓝、灵槐、平安、书翠、翠风、香巧、代云、友巧、听寒、梦柏、醉易、访旋、亦玉、凌萱、访卉、怀亦、笑蓝、春翠、靖柏、书雪、乐枫、念薇、靖雁、寻春、恨山、从寒、忆香、觅波、静曼、凡旋、新波、代真、新蕾、雁玉、冷卉、紫山、千琴、恨天、傲芙、盼山、怀蝶、冰兰、问旋、从南、白易、问筠、如霜、半芹、寒雁、怜云、寻文、谷雪、乐萱、涵菡、海莲、傲蕾、青槐、冬儿、易梦、惜雪、宛海、之柔、夏青";

    private static Random random = new Random();

    public static String build(){
        //随机生成2或3
        int length = random.nextInt(2)+2;

        //随机生成姓
        Random r = new Random();
        String[] firstNameArr = firstName.split(" ");
        int index = r.nextInt(firstNameArr.length);
        //姓
        String name = firstNameArr[index];

        //名
        while (name.length()<length){
            int secondIndex = r.nextInt(secondName.length());
            String second = secondName.substring(secondIndex, secondIndex + 1);
            if(!"、".equals(second)){
                 name += second;
            }else{
                continue;
            }
        }
        return name;
    }

}
