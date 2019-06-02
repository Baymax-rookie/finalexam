# finalexam

## 接口
### 登录接口
* /login 登录 
* /sign 注册
* /getuser 用户列表
* /initialize 初始化棋盘
* /getplayer 游戏中玩家列表
* /createroom 创房
* /websocket/personal/#{roomnum} 私人聊天室
* /websocket/#{roomnum} 公共聊天室
* //websocket/chess/#{chessnum} 游戏室中有下棋的功能
## 其他的内容
* 对websocket没有深入了解，所以功能全往ServerPoint写的，导致显得比较臃肿
* 房间都是在同一个，不过用实体类把功能分割了,PlayRoom 可以下旗，AbstractRoom只能看，但是都在一个聊天室
* 返回数据的工具类比较多，其实都没什么用
* 基本的棋盘规则写了点，然后没有时间钻研胜出了
* 存数据库回放，用move实体类表示棋子移动，然后用moves存数据库
