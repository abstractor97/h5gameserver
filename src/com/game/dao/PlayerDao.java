package com.game.dao;

import java.util.List;

import com.game.domain.player.Player;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

@DAO
public interface PlayerDao {

    //player
    @SQL("select * from player where binary accName = :accName")
    public List<Player> selectRoleList(@SQLParam("accName") String accName);

    @SQL("insert into player(playerId,accName,name,sex,vocation,regTime,serverId,channel,clientMac) values(:player.playerId,:player.accName,:player.name,:player.sex,:player.vocation,:player.regTime,:player.serverId,:player.channel,:player.clientMac)")
    public void insert(@SQLParam("player") Player player);

    @SQL("select max(playerId) from player")
    public Integer selectMaxPlayerId();

    @SQL("select * from player where playerId = :playerId")
    public Player select(@SQLParam("playerId") int playerId);

    @SQL("select playerId from player where name = :name limit 1")
    public Integer selectIdByName(@SQLParam("name") String name);

    @SQL("update player set exp=:p.exp,coin=:p.coin,diamond=:p.diamond,chargeDiamond=:p.chargeDiamond,vip=:p.vip,energy=:p.energy,serverId=:p.serverId,lastLoginTime=:p.lastLoginTime,lastLogoutTime=:p.lastLogoutTime,ip=:p.ip,sceneId=:p.sceneId,x=:p.x,y=:p.y,lastSceneId=:p.lastSceneId,lev=:p.lev,crit=:p.crit,hp=:p.hp,symptom=:p.symptom,fu=:p.fu,fight=:p.fight,z=:p.z,fashionId=:p.fashionId,gangId=:p.gangId,totalCoin=:p.totalCoin,weaponId=:p.weaponId,energyTime=:p.energyTime,totalDiamond=:p.totalDiamond,channel=:p.channel,attack=:p.attack,defense=:p.defense,title=:p.title,achievement=:p.achievement where playerId=:p.playerId")
    public void update(@SQLParam("p") Player player);

    //player_data
    @SQL("update player_data set data=:data where playerId = :playerId")
    public void updatePlayerData(@SQLParam("playerId") int playerId, @SQLParam("data") byte[] data);

    @SQL("insert into player_data(playerId,data) values(:playerId,:data)")
    public void insertPlayerData(@SQLParam("playerId") int playerId, @SQLParam("data") byte[] data);

    @SQL("select data from player_data where playerId = :playerId")
    public byte[] selectPlayerData(@SQLParam("playerId") int playerId);


    //@SQL("select playerId from player where playerId/1000>5000 and playerId not in (:exclude) and (fightStrength >= :min && fightStrength <= :max) limit :limitd ")
    //public List<Integer> selectFriends(@SQLParam("exclude")List<Integer> exclude,@SQLParam("max")int maxFight,@SQLParam("min")int minFight,@SQLParam("limitd")int limit);

    @SQL("select playerId from player where accName<>'sys' and playerId not in (:exclude) and (lev >= :min && lev <= :max)  limit :limitd ")
    public List<Integer> selectFriends(@SQLParam("exclude") List<Integer> exclude, @SQLParam("max") int maxLev, @SQLParam("min") int minLev, @SQLParam("limitd") int limit);

    @SQL("select playerId from player where accName='sys'")
    public List<Integer> selectRobots();

    @SQL("select playerId from player where playerId not in (:exclude) and fight <= :max order by fight desc limit :limit")
    public List<Integer> selectByFightingPower(@SQLParam("exclude") List<Integer> exclude, @SQLParam("max") int max, @SQLParam("limit") int limit);

    @SQL("select * from player where accName!='sys'")
    public List<Player> selectAllPlayer();

    @SQL("select * from player")
    public List<Player> selectPlayersAndRobots();

    //改名
    @SQL("update player set name = :name where playerId = :playerId")
    public void updatePlayerName(@SQLParam("playerId") int playerId, @SQLParam("name") String name);

    @SQL("REPLACE INTO t_charge_record VALUES (:accountName,:playerId,:nickName,:totalCharge,:loginDays)")
    public void insertChargerecord(@SQLParam("accountName") String accountName, @SQLParam("playerId") int playerId, @SQLParam("nickName") String nickName, @SQLParam("totalCharge") int totalCharge, @SQLParam("loginDays") int loginDays);
}
