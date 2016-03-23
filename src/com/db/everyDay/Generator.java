package com.db.everyDay;






import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jack lu on 2016/3/11.
 */
public class Generator {

    public static  void main(String[] args){
        Date driveDeadLine= new Date();

        //初始化车辆
        List<VirtualCar> virtualCarList=new ArrayList<VirtualCar>();
        virtualCarList.add(new VirtualCar(10001,"INCAR10010018545","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10002,"INCAR10010025919","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10003,"INCAR10010034220","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10004,"INCAR10010045366","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10005,"INCAR10010053833","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10006,"INCAR10010065368","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10007,"INCAR10010074317","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10008,"INCAR10010084352","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10009,"INCAR10010096441","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10010,"INCAR10010102450","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10011,"INCAR10010115783","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10012,"INCAR10010127680","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10013,"INCAR10010139787","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10014,"INCAR10010149646","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10015,"INCAR10010155588","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10016,"INCAR10010162622","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10017,"INCAR10010179318","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10018,"INCAR10010187980","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10019,"INCAR10010198325","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10020,"INCAR10010206426","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10021,"INCAR10010213361","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10022,"INCAR10010222431","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10023,"INCAR10010233232","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10024,"INCAR10010247175","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10025,"INCAR10010255786","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10026,"INCAR10010269178","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10027,"INCAR10010272235","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10028,"INCAR10010281783","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10029,"INCAR10010293451","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10030,"INCAR10010303272","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10031,"INCAR10010318484","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10032,"INCAR10010328530","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10033,"INCAR10010338476","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10034,"INCAR10010341362","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10035,"INCAR10010354432","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10036,"INCAR10010367754","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10037,"INCAR10010375429","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10038,"INCAR10010389981","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10039,"INCAR10010395156","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10040,"INCAR10010402354","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10041,"INCAR10010411892","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10042,"INCAR10010422207","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10043,"INCAR10010432550","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10044,"INCAR10010447621","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10045,"INCAR10010454679","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10046,"INCAR10010463165","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10047,"INCAR10010474743","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10048,"INCAR10010481233","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10049,"INCAR10010492774","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10050,"INCAR10010509428","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10051,"INCAR10010519117","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10052,"INCAR10010526439","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10053,"INCAR10010538806","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10054,"INCAR10010542961","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10055,"INCAR10010558778","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10056,"INCAR10010563542","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10057,"INCAR10010579170","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10058,"INCAR10010587213","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10059,"INCAR10010598024","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10060,"INCAR10010604314","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10061,"INCAR10010614405","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10062,"INCAR10010627676","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10063,"INCAR10010635294","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10064,"INCAR10010644455","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10065,"INCAR10010658973","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10066,"INCAR10010661759","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10067,"INCAR10010677409","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10068,"INCAR10010682535","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10069,"INCAR10010691438","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10070,"INCAR10010702606","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10071,"INCAR10010713376","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10072,"INCAR10010729661","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10073,"INCAR10010736719","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10074,"INCAR10010743434","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10075,"INCAR10010759821","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10076,"INCAR10010764321","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10077,"INCAR10010778159","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10078,"INCAR10010786062","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10079,"INCAR10010795742","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10080,"INCAR10010807313","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10081,"INCAR10010817040","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10082,"INCAR10010826228","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10083,"INCAR10010832620","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10084,"INCAR10010849259","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10085,"INCAR10010851803","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10086,"INCAR10010865300","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10087,"INCAR10010877536","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10088,"INCAR10010888755","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10089,"INCAR10010898431","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10090,"INCAR10010902859","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10091,"INCAR10010911310","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10092,"INCAR10010923450","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10093,"INCAR10010935548","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10094,"INCAR10010944701","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10095,"INCAR10010952558","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10096,"INCAR10010963535","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10097,"INCAR10010975214","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10098,"INCAR10010985428","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10099,"INCAR10010993117","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10100,"INCAR10011003548","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10101,"INCAR10011013087","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10102,"INCAR10011022681","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10103,"INCAR10011038189","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10104,"INCAR10011044326","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10105,"INCAR10011056728","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10106,"INCAR10011065885","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10107,"INCAR10011076491","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10108,"INCAR10011088337","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10109,"INCAR10011093981","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10110,"INCAR10011104478","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10111,"INCAR10011116419","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10112,"INCAR10011126421","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10113,"INCAR10011137461","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10114,"INCAR10011147753","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10115,"INCAR10011157462","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10116,"INCAR10011166324","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10117,"INCAR10011177827","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10118,"INCAR10011187020","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10119,"INCAR10011196598","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10120,"INCAR10011209841","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10121,"INCAR10011217207","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10122,"INCAR10011224839","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10123,"INCAR10011232815","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10124,"INCAR10011248109","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10125,"INCAR10011259848","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10126,"INCAR10011261032","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10127,"INCAR10011274288","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10128,"INCAR10011289056","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10129,"INCAR10011298304","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10130,"INCAR10011308148","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10131,"INCAR10011316545","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10132,"INCAR10011322732","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10133,"INCAR10011338958","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10134,"INCAR10011341361","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10135,"INCAR10011358796","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10136,"INCAR10011366441","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10137,"INCAR10011377604","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10138,"INCAR10011381912","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10139,"INCAR10011392267","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10140,"INCAR10011401629","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10141,"INCAR10011413340","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10142,"INCAR10011428141","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10143,"INCAR10011432355","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10144,"INCAR10011445878","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10145,"INCAR10011453956","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10146,"INCAR10011467633","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10147,"INCAR10011478011","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10148,"INCAR10011485225","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10149,"INCAR10011496824","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10150,"INCAR10011501657","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10151,"INCAR10011513351","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10152,"INCAR10011527613","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10153,"INCAR10011533590","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10154,"INCAR10011543650","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10155,"INCAR10011554692","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10156,"INCAR10011562822","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10157,"INCAR10011573558","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10158,"INCAR10011589086","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10159,"INCAR10011599549","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10160,"INCAR10011604881","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10161,"INCAR10011612147","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10162,"INCAR10011623553","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10163,"INCAR10011631283","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10164,"INCAR10011643775","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10165,"INCAR10011653619","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10166,"INCAR10011664956","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10167,"INCAR10011676285","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10168,"INCAR10011688368","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10169,"INCAR10011697419","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10170,"INCAR10011706088","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10171,"INCAR10011718682","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10172,"INCAR10011728758","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10173,"INCAR10011735746","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10174,"INCAR10011747732","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10175,"INCAR10011759347","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10176,"INCAR10011764791","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10177,"INCAR10011779161","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10178,"INCAR10011787326","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10179,"INCAR10011792150","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10180,"INCAR10011806366","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10181,"INCAR10011812539","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10182,"INCAR10011825947","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10183,"INCAR10011834810","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10184,"INCAR10011847936","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10185,"INCAR10011853829","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10186,"INCAR10011868340","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10187,"INCAR10011871928","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10188,"INCAR10011883827","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10189,"INCAR10011899124","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10190,"INCAR10011906838","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10191,"INCAR10011911507","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10192,"INCAR10011925663","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10193,"INCAR10011931398","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10194,"INCAR10011942649","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10195,"INCAR10011957039","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10196,"INCAR10011968539","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10197,"INCAR10011979100","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10198,"INCAR10011981960","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10199,"INCAR10011994371","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10200,"INCAR10012002316","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10201,"INCAR10012015129","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10202,"INCAR10012024799","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10203,"INCAR10012038930","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10204,"INCAR10012048089","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10205,"INCAR10012056069","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10206,"INCAR10012066037","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10207,"INCAR10012071405","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10208,"INCAR10012089909","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10209,"INCAR10012096981","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10210,"INCAR10012101312","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10211,"INCAR10012113975","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10212,"INCAR10012128687","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10213,"INCAR10012137679","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10214,"INCAR10012141466","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10215,"INCAR10012159268","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10216,"INCAR10012168898","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10217,"INCAR10012173978","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10218,"INCAR10012189729","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10219,"INCAR10012191481","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10220,"INCAR10012206108","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10221,"INCAR10012211437","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10222,"INCAR10012224786","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10223,"INCAR10012237633","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10224,"INCAR10012246988","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10225,"INCAR10012251896","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10226,"INCAR10012266758","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10227,"INCAR10012279809","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10228,"INCAR10012285984","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10229,"INCAR10012298016","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10230,"INCAR10012309873","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10231,"INCAR10012316757","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10232,"INCAR10012322596","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10233,"INCAR10012332341","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10234,"INCAR10012344303","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10235,"INCAR10012352565","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10236,"INCAR10012361311","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10237,"INCAR10012379045","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10238,"INCAR10012386102","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10239,"INCAR10012395686","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10240,"INCAR10012409429","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10241,"INCAR10012416626","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10242,"INCAR10012424002","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10243,"INCAR10012438145","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10244,"INCAR10012446011","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10245,"INCAR10012456911","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10246,"INCAR10012464533","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10247,"INCAR10012474978","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10248,"INCAR10012481074","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10249,"INCAR10012492244","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10250,"INCAR10012503081","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10251,"INCAR10012511636","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10252,"INCAR10012526820","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10253,"INCAR10012539697","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10254,"INCAR10012549349","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10255,"INCAR10012553484","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10256,"INCAR10012568146","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10257,"INCAR10012576376","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10258,"INCAR10012589796","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10259,"INCAR10012592237","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10260,"INCAR10012607621","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10261,"INCAR10012616117","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10262,"INCAR10012626748","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10263,"INCAR10012631121","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10264,"INCAR10012642774","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10265,"INCAR10012657311","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10266,"INCAR10012669713","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10267,"INCAR10012672072","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10268,"INCAR10012682728","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10269,"INCAR10012694727","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10270,"INCAR10012705778","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10271,"INCAR10012711436","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10272,"INCAR10012724926","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10273,"INCAR10012735959","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10274,"INCAR10012747921","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10275,"INCAR10012759984","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10276,"INCAR10012767187","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10277,"INCAR10012773708","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10278,"INCAR10012782319","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10279,"INCAR10012797768","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10280,"INCAR10012806805","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10281,"INCAR10012819016","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10282,"INCAR10012823685","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10283,"INCAR10012837527","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10284,"INCAR10012847797","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10285,"INCAR10012854932","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10286,"INCAR10012863740","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10287,"INCAR10012879521","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10288,"INCAR10012885115","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10289,"INCAR10012894814","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10290,"INCAR10012904607","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10291,"INCAR10012919517","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10292,"INCAR10012923687","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10293,"INCAR10012938188","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10294,"INCAR10012942020","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10364,"INCAR10013642222","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10365,"INCAR10013659478","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10366,"INCAR10013661867","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10367,"INCAR10013677790","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10368,"INCAR10013685749","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10369,"INCAR10013692057","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10370,"INCAR10013707324","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10371,"INCAR10013715244","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10372,"INCAR10013729759","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10373,"INCAR10013732153","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10374,"INCAR10013743160","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10375,"INCAR10013751218","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10376,"INCAR10013766007","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10377,"INCAR10013773216","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10378,"INCAR10013784033","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10379,"INCAR10013794567","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10380,"INCAR10013809572","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10381,"INCAR10013815271","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10382,"INCAR10013827399","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10383,"INCAR10013837187","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10384,"INCAR10013842934","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10385,"INCAR10013852729","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10386,"INCAR10013869843","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10387,"INCAR10013872089","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10388,"INCAR10013882066","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10389,"INCAR10013899307","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10390,"INCAR10013907180","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10391,"INCAR10013917525","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10392,"INCAR10013925644","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10393,"INCAR10013931766","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10394,"INCAR10013942636","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10395,"INCAR10013955241","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10396,"INCAR10013966416","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10397,"INCAR10013975762","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10398,"INCAR10013983015","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10399,"INCAR10013993864","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10400,"INCAR10014005546","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10401,"INCAR10014013294","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10402,"INCAR10014021259","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10403,"INCAR10014037844","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10404,"INCAR10014044121","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10405,"INCAR10014053975","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10406,"INCAR10014067288","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10407,"INCAR10014073794","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10408,"INCAR10014082953","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10409,"INCAR10014092549","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10410,"INCAR10014107304","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10411,"INCAR10014117560","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10412,"INCAR10014126561","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10413,"INCAR10014138402","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10414,"INCAR10014145038","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10415,"INCAR10014153936","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10416,"INCAR10014166215","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10417,"INCAR10014172247","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10418,"INCAR10014181675","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10419,"INCAR10014194410","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10420,"INCAR10014204326","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10421,"INCAR10014217102","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10422,"INCAR10014221325","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10423,"INCAR10014232702","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10424,"INCAR10014247337","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10425,"INCAR10014258230","",driveDeadLine));
        virtualCarList.add(new VirtualCar(10426,"INCAR10014264857","",driveDeadLine));



        long start=new Date().getTime();
        File log=new File("d:\\db\\do-not-exe-me.txt");
        log.delete();
        File condition=new File("d:\\db\\condition.sql");
        condition.delete();
        File detail=new File("d:\\db\\detail.sql");
        detail.delete();
        File drive=new File("d:\\db\\drive.sql");
        drive.delete();
        File location=new File("d:\\db\\location.sql");
        location.delete();
        for (int i = 0; i < 1; i++) {
            VirtualCar virtualCar=virtualCarList.get(i);
            virtualCar.initDate(new Date());//初始化时间
            virtualCar.driveOneDay();//
        }

        long end=new Date().getTime();
        Tools.writeGloablTxt("-- 耗时:" + (end - start) + "ms");
    }
}