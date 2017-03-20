package com.example.descriptmap.popup;

import android.location.Location;

/**
 * Created by 岗哥 on 2017/2/21.
 */
public class CquptPoint {

    public CquptPoint(){

    }

    public String getLocation(Location location)
    {
        String address="";
        double radius=1.0013969541303655E-4;
        double radius1=3.332870742764541E-4;
        double radius2=8.923554824698603E-5;
        double radius3=2.332870742764541E-4;
        if (calculate(location,getCenterLocation("shuzi"),radius1)) {
        	address="数字图书馆";
        }
        else if (calculate(location,getCenterLocation("xinxiaomen"),radius)) {
        	address="新校门";
        }
         else if (calculate(location,getCenterLocation("laoxiaomen"),radius)) {
        	address="老校门";
        }
         else if (calculate(location,getCenterLocation("bajiao"),radius1)) {
        	address="八教";
        }
         else if (calculate(location,getCenterLocation("wujiao"),radius1)) {
        	address="五教";
        }
         else if (calculate(location,getCenterLocation("sijiao"),radius)) {
        	address="四教";
        }
         else if (calculate(location,getCenterLocation("yifu"),radius1)) {
        	address="逸夫楼";
        }
         else if (calculate(location,getCenterLocation("sanjiao"),radius1)) {
        	address="三教";
        }
         else if (calculate(location,getCenterLocation("jinguan"),radius1)) {
        	address="经济管理学院";
        }
              else if (calculate(location,getCenterLocation("fengyu"),radius1)) {
        	address="风雨操场";
        }
         else if (calculate(location,getCenterLocation("youyong"),radius2)) {
        	address="游泳池";
        }
         else if (calculate(location,getCenterLocation("taiji"),radius3)) {
        	address="太极操场";
        }
         else if (calculate(location,getCenterLocation("wudonglanqiu"),radius1)) {
        	address="5栋篮球场";
        }
         else if (calculate(location,getCenterLocation("laocaochang"),radius1)) {
        	address="老操场";
        }
        else if (calculate(location,getCenterLocation("laotu"),radius)) {
            address="老图书馆";
        }
         else if (calculate(location,getCenterLocation("erjiao"),radius)) {
        	address="二教";
        }
         else if (calculate(location,getCenterLocation("bashiwan"),radius)) {
        	address="八十万";
        }
         else if (calculate(location,getCenterLocation("kejihui"),radius)) {
        	address="科技会堂";
        }
         else if (calculate(location,getCenterLocation("yiyuan"),radius)) {
        	address="重邮医院";
        }
         else if (calculate(location,getCenterLocation("binguan"),radius)) {
        	address="重邮宾馆";
        }
         else if (calculate(location,getCenterLocation("xinke"),radius)) {
        	address="信息科技大厦";
        }
         else if (calculate(location,getCenterLocation("lixueyuan"),radius)) {
        	address="理学院";
        }
         else if (calculate(location,getCenterLocation("yijiao"),radius)) {
        	address="一教";
        }
         else if (calculate(location,getCenterLocation("faxueyuan"),radius)) {
        	address="法学院";
        }
         else if (calculate(location,getCenterLocation("daxibei"),radius)) {
        	address="大西北";
        }
         else if (calculate(location,getCenterLocation("zhongxin"),radius)) {
        	address="中心食堂";
        }
              else if (calculate(location,getCenterLocation("qianxihe"),radius)) {
        	address="千喜鹤|延生";
        }
         else if (calculate(location,getCenterLocation("12lanqiu"),radius)) {
        	address="12栋篮球场";
        }
         else if (calculate(location,getCenterLocation("qingrenpo"),radius)) {
        	address="情人坡";
        }
         else if (calculate(location,getCenterLocation("jiaxiao"),radius)) {
        	address="重邮驾校";
        }
         else if (calculate(location,getCenterLocation("1dong"),radius2)) {
        	address="1栋";
        }
         else if (calculate(location,getCenterLocation("2dong"),radius2)) {
        	address="2栋";
        }
         else if (calculate(location,getCenterLocation("3dong"),radius2)) {
        	address="3栋";
        }
         else if (calculate(location,getCenterLocation("4dong"),radius)) {
        	address="4栋";
        }
         else if (calculate(location,getCenterLocation("5dong"),radius)) {
        	address="5栋";
        }
          else if (calculate(location,getCenterLocation("6dong"),radius)) {
        	address="6栋";
        }
         else if (calculate(location,getCenterLocation("8dong"),radius)) {
        	address="8栋";
        }
         else if (calculate(location,getCenterLocation("9dong"),radius)) {
        	address="9栋";
        }
         else if (calculate(location,getCenterLocation("10dong"),radius)) {
        	address="10栋";
        }
         else if (calculate(location,getCenterLocation("11dong"),radius)) {
        	address="11栋";
        }
           else if (calculate(location,getCenterLocation("12dong"),radius)) {
        	address="12栋";
        }
         else if (calculate(location,getCenterLocation("13dong"),radius)) {
        	address="13栋";
        }
         else if (calculate(location,getCenterLocation("14dong"),radius)) {
        	address="14栋";
        }
         else if (calculate(location,getCenterLocation("15dong"),radius)) {
        	address="15栋";
        }
         else if (calculate(location,getCenterLocation("16dong"),radius)) {
        	address="16栋";
        }
          else if (calculate(location,getCenterLocation("17dong"),radius)) {
        	address="17栋";
        }
         else if (calculate(location,getCenterLocation("18dong"),radius)) {
        	address="18栋";
        }
         else if (calculate(location,getCenterLocation("19dong"),radius)) {
        	address="19栋";
        }
         else if (calculate(location,getCenterLocation("20dong"),radius)) {
        	address="20栋";
        }
         else if (calculate(location,getCenterLocation("21dong"),radius)) {
        	address="21栋";
        }
           else if (calculate(location,getCenterLocation("22dong"),radius)) {
        	address="22栋";
        }
         else if (calculate(location,getCenterLocation("23dong"),radius)) {
        	address="23栋";
        }
         else if (calculate(location,getCenterLocation("24dong"),radius)) {
        	address="24栋";
        }
         else if (calculate(location,getCenterLocation("25dong"),radius)) {
        	address="25栋";
        }
         else if (calculate(location,getCenterLocation("26dong"),radius2)) {
        	address="26栋";
        }
          else if (calculate(location,getCenterLocation("27dong"),radius2)) {
        	address="27栋";
        }
         else if (calculate(location,getCenterLocation("28dong"),radius)) {
        	address="28栋";
        }
         else if (calculate(location,getCenterLocation("29dong"),radius)) {
        	address="29栋";
        }
         else if (calculate(location,getCenterLocation("30dong"),radius)) {
        	address="30栋";
        }
         else if (calculate(location,getCenterLocation("honggaoliang"),radius)) {
        	address="红高粱";
        }
        else if (calculate(location,getCenterLocation("31dong"),radius)) {
        	address="31栋";
        }
        else if (calculate(location,getCenterLocation("32dong"),radius)) {
        	address="32栋";
        }
        else if (calculate(location,getCenterLocation("33dong"),radius)) {
        	address="33栋";
        }
          else if (calculate(location,getCenterLocation("34dong"),radius2)) {
        	address="34栋";
        }
        else if (calculate(location,getCenterLocation("35dong"),radius)) {
        	address="35栋";
        }
        else if (calculate(location,getCenterLocation("36dong"),radius2)) {
        	address="36栋";
        }
         else if (calculate(location,getCenterLocation("37dong"),radius)) {
        	address="37栋";
        }
   
        return  address;
    }

    private  boolean calculate(Location location,Location center,double radius) {
        boolean in=false;
        //|(x-x0)|平方+|（y-y0）|平方<=半径的平方就说明在圆内
        if (Math.pow(location.getLatitude()-center.getLatitude(),2)
                +Math.pow(location.getLongitude()-center.getLongitude(),2)
                <=Math.pow(radius,2)){
            in=true;
        }
        else {
            in=false;
        }

        return in;

    }

    private  Location getCenterLocation(String center)
    {
        Location centerLocation=new Location(center);

        if (center.equals("shuzi")){
            centerLocation.setLongitude(106.61023975067465);
            centerLocation.setLatitude(29.52287637191772);
        }
        else if (center.equals("xinxiaomen")){
            centerLocation.setLongitude(106.61029389622654);
            centerLocation.setLatitude(29.520956018218993);
        }
        else if (center.equals("laoxiaomen")){
            centerLocation.setLongitude(106.61012243531674);
            centerLocation.setLatitude(29.519184336814877);
        }
          else if (center.equals("bajiao")){
            centerLocation.setLongitude(106.6102886327551);
            centerLocation.setLatitude(29.53467106195152);
        }
        else if (center.equals("laotu")){
            centerLocation.setLongitude(106.6099389420383);
            centerLocation.setLatitude(29.524102980684815);
        }
        else if (center.equals("bajiao1")){
            centerLocation.setLongitude(106.61028374393543);
            centerLocation.setLatitude(29.53453477876097);
        }
        else if (center.equals("bajiao2")){
            centerLocation.setLongitude(106.61030856064784);
            centerLocation.setLatitude(29.534361327434183);
        }
        else if (center.equals("bajiao3")){
            centerLocation.setLongitude(106.6102623113204);
            centerLocation.setLatitude(29.534396106195154);
        }
          else if (center.equals("wujiao")){
            centerLocation.setLongitude(106.6103612021539);
            centerLocation.setLatitude(29.533295840702056);
        }
          else if (center.equals("sijiao")){
            centerLocation.setLongitude(106.61036496226117);
            centerLocation.setLatitude(29.529108230171204);
        }
          else if (center.equals("yifu")){
            centerLocation.setLongitude(106.61034616172478);
            centerLocation.setLatitude(29.526729468784332);
        }
          else if (center.equals("sanjiao")){
            centerLocation.setLongitude(106.6102822398922);
            centerLocation.setLatitude(29.530669291934966);
        }
          else if (center.equals("jinguan")){
            centerLocation.setLongitude(106.61019801347638);
            centerLocation.setLatitude(29.532787876138688);
        }
          else if (center.equals("fengyu")){
            centerLocation.setLongitude(106.61019462938314);
            centerLocation.setLatitude(29.52957902666092);
        }
          else if (center.equals("youyong")){
            centerLocation.setLongitude(106.61013559569221);
            centerLocation.setLatitude(29.52889761053085);
        }
          else if (center.equals("taiji")){
            centerLocation.setLongitude(106.61009423449552);
            centerLocation.setLatitude(29.53168522139549);
        }
          else if (center.equals("wudonglanqiu")){
            centerLocation.setLongitude(106.61005625740867);
            centerLocation.setLatitude(29.528612654743196);
        }
          else if (center.equals("laocaochang")){
            centerLocation.setLongitude(106.610082954173368);
            centerLocation.setLatitude(29.526407345256803);
        }
          else if (center.equals("erjiao")){
            centerLocation.setLongitude(106.61004723315453);
            centerLocation.setLatitude(29.523817964897155);
        }
          else if (center.equals("bashiwan")){
            centerLocation.setLongitude(106.61006791374457);
            centerLocation.setLatitude(29.521042743835448);
        }
          else if (center.equals("kejihui")){
            centerLocation.setLongitude(106.61007468194765);
            centerLocation.setLatitude(29.518527699623107);
        }
          else if (center.equals("yiyuan")){
            centerLocation.setLongitude(106.61003933692592);
            centerLocation.setLatitude(29.51805690246582);
        }

          else if (center.equals("binguan")){
            centerLocation.setLongitude(106.60994307817626);
            centerLocation.setLatitude(29.518168407020568);
        }
          else if (center.equals("xinke")){
            centerLocation.setLongitude(106.60994157412004);
            centerLocation.setLatitude(29.519890530548093);
        }
          else if (center.equals("lixueyuan")){
            centerLocation.setLongitude(106.60989908491445);
            centerLocation.setLatitude(29.521637433013915);
        }
          else if (center.equals("yijiao")){
            centerLocation.setLongitude(106.6099645107944);
            centerLocation.setLatitude(29.52138964630127);
        }
          else if (center.equals("faxueyuan")){
            centerLocation.setLongitude(106.61000361589014);
            centerLocation.setLatitude(29.520733009109495);
        }
          else if (center.equals("daxibei")){
            centerLocation.setLongitude(106.60994270214557);
            centerLocation.setLatitude(29.526407345256803);
        }
          else if (center.equals("zhongxin")){
            centerLocation.setLongitude(106.6099588706338);
            centerLocation.setLatitude(29.527026814041136);
        }
          else if (center.equals("qianxihe")){
            centerLocation.setLongitude(106.60986637197446);
            centerLocation.setLatitude(29.528686990890503);
        }
          else if (center.equals("12lanqiu")){
            centerLocation.setLongitude(106.60986524394893);
            centerLocation.setLatitude(29.526134778938292);
        }
          else if (center.equals("qingrenpo")){
            centerLocation.setLongitude(106.60985621967815);
            centerLocation.setLatitude(29.524239203510284);
        }
        else if (center.equals("jiaxiao")){
            centerLocation.setLongitude(106.60988667653382);
            centerLocation.setLatitude(29.517709999999997);
        }
          else if (center.equals("1dong")){
            centerLocation.setLongitude(106.60991450134098);
            centerLocation.setLatitude(29.52893477860451);
        }
          else if (center.equals("2dong")){
            centerLocation.setLongitude(106.60994157412004);
            centerLocation.setLatitude(29.5289595575428);
        }
          else if (center.equals("3dong")){
            centerLocation.setLongitude(106.60997090295017);
            centerLocation.setLatitude(29.528971947011946);
        }
          else if (center.equals("4dong")){
            centerLocation.setLongitude(106.61001038409321);
            centerLocation.setLatitude(29.529938318595885);
        }
          else if (center.equals("5dong")){
            centerLocation.setLongitude(106.61000587197447);
            centerLocation.setLatitude(29.528848053321838);
        }
          else if (center.equals("6dong")){
            centerLocation.setLongitude(106.60997503908813);
            centerLocation.setLatitude(29.530235663852693);
        }
          else if (center.equals("8dong")){
            centerLocation.setLongitude(106.60978327358364);
            centerLocation.setLatitude(29.52596132770538);
        }
          else if (center.equals("9dong")){
            centerLocation.setLongitude(106.60981147438824);
            centerLocation.setLatitude(29.525986105976102);
        }
          else if (center.equals("10dong")){
            centerLocation.setLongitude(106.60984005122351);
            centerLocation.setLatitude(29.525973716506957);
        }
          else if (center.equals("11dong")){
            centerLocation.setLongitude(106.60990322101914);
            centerLocation.setLatitude(29.5260108849144);
        }
          else if (center.equals("12dong")){
            centerLocation.setLongitude(106.60989908491445);
            centerLocation.setLatitude(29.527187875804902);
        }
          else if (center.equals("15dong")){
            centerLocation.setLongitude(106.61000587197447);
            centerLocation.setLatitude(29.531648052988054);
        }
          else if (center.equals("16dong")){
            centerLocation.setLongitude(106.60997616711366);
            centerLocation.setLatitude(29.531946283159258);
        }
          else if (center.equals("17dong")){
            centerLocation.setLongitude(106.61004873719412);
            centerLocation.setLatitude(29.534274601776005);
        }
          else if (center.equals("18dong")){
            centerLocation.setLongitude(106.61007392991954);
            centerLocation.setLatitude(29.534299380526544);
        }

          else if (center.equals("19dong")){
            centerLocation.setLongitude(106.61012393935633);
            centerLocation.setLatitude(29.534225044254062);
        }
          else if (center.equals("20dong")){
            centerLocation.setLongitude(106.61014875606769);
            centerLocation.setLatitude(29.534286991140842);
        }
          else if (center.equals("23dong")){
            centerLocation.setLongitude(106.61015101212708);
            centerLocation.setLatitude(29.53613300877575714);
        }
          else if (center.equals("22dong")){
            centerLocation.setLongitude(106.61006227358365);
            centerLocation.setLatitude(29.535947168073655);
        }
          else if (center.equals("21dong")){
            centerLocation.setLongitude(106.61000850403958);
            centerLocation.setLatitude(29.535996725616457);
        }
          else if (center.equals("honggaoliang")){
            centerLocation.setLongitude(106.60998970351982);
            centerLocation.setLatitude(29.534267433618905);
        }
          else if (center.equals("24dong")){
            centerLocation.setLongitude(106.60993555796169);
            centerLocation.setLatitude(29.530731238946913);
        }
          else if (center.equals("25dong")){
            centerLocation.setLongitude(106.60990773315453);
            centerLocation.setLatitude(29.53043389369011);
        }
          else if (center.equals("26dong")){
            centerLocation.setLongitude(106.60988141240358);
            centerLocation.setLatitude(29.53033477860451);
        }
          else if (center.equals("27dong")){
            centerLocation.setLongitude(106.6098486994636);
            centerLocation.setLatitude(29.530396725616455);
        } else if (center.equals("28dong")){
            centerLocation.setLongitude(106.60982012262832);
            centerLocation.setLatitude(29.530384336147307);
        }
          else if (center.equals("29dong")){
            centerLocation.setLongitude(106.60979154582631);
            centerLocation.setLatitude(29.53042150455475);
        }
          else if (center.equals("30dong")){
            centerLocation.setLongitude(106.60976296899103);
            centerLocation.setLatitude(29.530371947011947);
        }
          else if (center.equals("31dong")){
            centerLocation.setLongitude(106.60979304984926);
            centerLocation.setLatitude(29.528575487003327);
        }
          else if (center.equals("32dong")){
            centerLocation.setLongitude(106.60976334502172);
            centerLocation.setLatitude(29.52851353965759);
        }
          else if (center.equals("35dong")){
            centerLocation.setLongitude(106.60972912802552);
            centerLocation.setLatitude(29.528875487003327);
        }

          else if (center.equals("34dong")){
            centerLocation.setLongitude(106.609742288401);
            centerLocation.setLatitude(29.52617194667816);
        }
          else if (center.equals("33dong")){
            centerLocation.setLongitude(106.60972123178028);
            centerLocation.setLatitude(29.526927698955536);
        }
          else if (center.equals("36dong")){
            centerLocation.setLongitude(106.60968626278924);
            centerLocation.setLatitude(29.526828583869932);
        }
          else if (center.equals("37dong")){
            centerLocation.setLongitude(106.60970543935632);
            centerLocation.setLatitude(29.526134778938292);
        }
        


        return centerLocation;

    }
}
