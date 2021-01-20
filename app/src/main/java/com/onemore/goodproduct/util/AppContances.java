package com.onemore.goodproduct.util;

/**
 * Created by reeta.zou on 2016/6/29.
 * 項目中需要用到的一些靜態常量值
 */
public class AppContances {
    public static final String TAB_ID_CREATE_ORDER = "create_order"; //市价开仓tag
    public static final String TAB_ID_PENDING_ORDER = "pending_order"; //挂单开仓tag
    public static final String TAB_ID_PENDING_ORDER_FAILURE = "pending_order_fail"; //挂单开仓失败tag
    public static final String TAB_ID_CLOSE_ORDER = "close_order"; //平仓tag
    public static final String TAB_ID_MODIFY_ORDER = "modify_order"; //设置止损止盈tag
    public static final String TAB_ID_PENDING_MODIFY_ORDER = "pending_modify_order"; //挂单改单tag
    public static final String TAB_ID_FAILURE_ORDER = "fail_order"; //订单失败tag
    public static final String TAB_ID_SUC_ORDER = "suc_order"; //订单成功tag
    /**
     * webview界面key
     */
    public final static String TAB_ID_WEBPAGE = "web_page";
    /**
     * 交易界面
     */
    public final static int TRADE_TYPE_FORM_QUOTE = 0;
    /**
     * 图表界面
     */
    public final static int TRADE_TYPE_FORM_CHART = 1;
    /**
     * 持仓界面
     */
    public final static int TRADE_TYPE_FORM_TRADE = 2;
    /**
     * 持仓界面-按产品
     */
    public final static int TRADE_TYPE_FORM_TRADE_PRODUCT = 6;
    /**
     * 搜索界面
     */
    public final static int TRADE_TYPE_FORM_SEARCH = 3;
    /**
     * 首页
     */
    public final static int TRADE_TYPE_FORM_HOME = 4;
    /**
     * 新订单的筛选
     */
    public final static int TRADE_TYPE_FORM_NEW_ORDER = 5;
    /**
     * 消息中心界面
     */
    public final static int TYPE_FORM_BULLETIN = 14;
    /**
     * 通知栏
     */
    public final static int TYPE_FORM_NOTIFY = 15;
    public static final int FROM_LOGIN_ACTIVITY = 17;//从登录界面开启面板界面
    public final static int REQUEST_SYSTEM_CODE = 100;//系统界面以及其他界面请求的code
    public final static int REQUEST_CHART_CODE = 101;//图表界面请求的code
    public final static int REQUEST_TRADE_CODE = 102;//交易界面请求的code
    public final static int REQUEST_SELECT_PRODUCT_CODE = 103;//图表界面请求的code
    public final static int RESULT_CODE = 200;//下单界面返回的code
    public final static int RESULT_LANG_CODE = 2000;//切换语言界面返回的code

    //登录回调，从哪里来，到哪里去
    public final static int REQUEST_TO_LOGIN_TRADE_CODE = 201;//从首页进入到交易，需要登录回调
    public final static int REQUEST_TO_LOGIN_ME_CODE = 202;//个人中心登录，需要登录回调
    public final static int REQUEST_TO_LOGIN_CHART_CODE = 203;//图表登录，需要登录回调
    public final static int REQUEST_TO_LOGIN_MESSAGE_CODE = 204;//消息页面登陆,需要回调
    public final static int RESULT_LOGIN_CODE = 1;//登录成功后回调code
    public final static int RESULT_LOGIN_DOME_CODE = 1006;//同时需要切换模拟账户

    public final static int CHANGE_ACCOUNT_RESULT_CODE = 18;//模拟账号填写之后，切换成真实账户，返回的code
    public final static int CHANGE_ACCOUNT_REQUST_CODE = 1;//模拟账号填写之后，切换成真实账户，回调，fragment
    public final static int FINISH_ACCOUNT_INFO = 204;//完善资料成功
    public final static int REQUEST_TO_MYINFO = 205;//实名认证个人资料成功
    public final static int OPEN_REAL_ACCOUNT_SUC = 206;//注册成功未完善资料

    public static final int REQUEST_COUNTRY = 207;//选择国家
    public static final int REQUEST_NATIONALITY = 208;//选择国家

    /**
     * 权限返回值
     */
    public final static int REQUEST_PERMISSION_ACCESS_STORAGE = 1;
    public final static int REQUEST_PERMISSION_ACCESS_PHONE = 2;
    public static final int REQUEST_CODE_PICK_IMAGE = 3;
    public static final int REQUEST_CODE_IMAGE_CAPTURE = 4;
    public final static int REQUEST_PERMISSION_ACCESS_CAMERA = 5;
    public final static int REQUEST_PERMISSION_READ_CONTACTS = 6;
    public final static String WEEK_1 = "Monday";
    public final static String WEEK_2 = "Tuesday";
    public final static String WEEK_3 = "Wednesday";
    public final static String WEEK_4 = "Thursday";
    public final static String WEEK_5 = "Friday";
    public final static String WEEK_6 = "Saturday";
    public final static String WEEK_7 = "Sunday";
    public static final int ORDER_TIME_OUT = 60 * 1000;//交易超时时间设定
    public static final int WEBVIEW_TIME_OUT = 60 * 1000;//网页等http请求超时时间设定
    public static final int SERVER_TIME_OUT = 30 * 1000;//交易行情请求超时时间设定
    public static final int TIMER_TASK_TIME = 200;//项目中一些定时时间
    public static final int TIMER_ORDER_TASK_TIME = 5000;//项目中订单提示定时时间
    public static final int TIMER_LOADING_TASK_TIME = 10 * 1000;//项目中加载圈定时时间
    public static final int TIMER_DISCONNECT_TASK_TIME = 180 * 1000;//项目中进入后台断服务器定时时间
    public static final int TIMER_HOTQUOTE_TASK_TIME = 300 * 1000;//热门行情产品定时刷新5min
    public static final int HANDLER_TIME_OUT = 0x001;//超时定义
    public static final int HANDLER_RESULT_SUC = 0x002;//交易成功
    public static final int HANDLER_SERVER_FAIL = 0x003;//服务器回应失败
    public static final int HANDLER_SHOW_VIEW = 0x005; //展开界面
    public static final int HANDLER_SCR_UPDATE_INPUTPRICE = 0x006;//更新界面输入的价格时需要同时调整平仓限价和停损价格
    public static final int HANDLER_SCR_UPDATE_INPUTLOT = 0x007;//更新界面输入的手数时需要同时调整平仓预期止损止盈值
    public static final int HANDLER_TIMER_TASK = 0x009;//定时器有关的通知
    public static final int HANDLER_ORDER_TIME_OUT = 1010;//交易超时错误码
    public static final int HANDLER_SHARE_SUCCESS = 1011;//分享成功
    public static final int HANDLER_SHARE_FAIL = 1012;//分享是吧
    public static final int HANDLER_SHARE_CANCEL = 1013;//分享取消
    /**
     * 登录状态
     */
    public static final int LOGIN_OK = 0;
    public static final int LOGIN_NONE = 1;
    public static final int LOGIN_DOING = 2;
    public static final int LOGIN_FAILED = 3;
    public static final int LOGIN_TIMEOUT = 4;
    public static final int LOGIN_CANCEL = 5;
    public static final int LOGIN_FORCE = 6; //强制登出
    /**
     * 涨
     */
    public static final int PRICE_INCREASE = 1;
    /**
     * 跌
     */
    public static final int PRICE_DECREASE = -1;
    /**
     * 平
     */
    public static final int PRICE_UNCHANGE = 0;
    /**
     * 无持仓
     */
    public static final int PRICE_NO_POSITION = 2;
    /**
     * 买卖现价的小三角和箭头显示
     */
    public static final int ARRAW_SHOW = 0;
    /**
     * 买卖现价的小三角和箭头不显示
     */
    public static final int ARRAW_HIDE = 1;
    /**
     * 买
     */
    public final static int TRADE_TYPE_BUY = 1;
    /**
     * 卖
     */
    public final static int TRADE_TYPE_SALE = 2;
    /**
     * 当日有效
     */
    public final static int VALID_TYPE_DAY = 1;
    /**
     * 本周有效
     */
    public final static int VALID_TYPE_WEEK = 2;
    /**
     * 市价
     */
    public final static int ORDER_TYPE_MARKET = 10;
    /**
     * 挂单
     */
    public final static int ORDER_TYPE_APPLY = 11;
    /**
     * 平仓
     */
    public final static int ORDER_TYPE_CLOSE = 12;
    /**
     * 改单
     */
    public final static int ORDER_TYPE_APPLY_MODIFY = 13;
    /**
     * 设止损止盈
     */
    public final static int ORDER_TYPE_MODIFY = 14;
    /**
     * 撤单
     */
    public final static int ORDER_TYPE_ORDER_CANCEL = 15;
    /**
     * 撤单
     */
    public final static int ORDER_TYPE_ONEKEY_CLOSE = 30;
    /**
     * 持仓预警
     */
    public final static int WRAN_TYPE_DELETE = 40;
    public final static int WRAN_TYPE_ADD = 41;
    public final static int WRAN_TYPE_UPDATE = 42;
    /**
     * 止盈开仓/平仓
     */
    public final static int ORDER_LIMIT_PRICE = 1;
    /**
     * 止损开仓/平仓
     */
    public final static int ORDER_STOP_PRICE = 2;
    /**
     * 市价开仓
     */
    public final static int ORDER_TYPE_MARKET_TYPE = 0x01;
    /**
     * 限价开仓
     */
    public final static int ORDER_TYPE_APPLY_TYPE = 0x02;
    /**
     * 停损开仓
     */
    public final static int ORDER_TYPE_APPLY_STOP_TYPE = 0x04;
    /**
     * 市价平仓
     */
    public final static int ORDER_TYPE_CLOSE_TYPE = 0x08;
    /**
     * 止损执行
     */
    public final static int ORDER_TYPE_STOP_TYPE = 0x10;
    /**
     * 止盈执行
     */
    public final static int ORDER_TYPE_LIMIT_TYPE = 0x20;
    /**
     * 强平执行
     */
    public final static int ORDER_TYPE_FORCE_CLOSE_TYPE = 0x40;
    /**
     * 期油到期平仓
     */
    public final static int SYS_ORDER_TYPE_FORCE_CLOSE_TYPE = 0xA0;
    /**
     * 部分平仓执行
     */
    public final static int ORDER_TYPE_PART_CLOSE_TYPE = 0x80;
    /**
     * 强平执行时需要过滤的数据
     */
    public final static int ORDER_TYPE_FORCE_CLOSE_TYPE2 = 0x84;
    /**
     * 价格点数设置类型
     */
    public final static int PRICE_TYPE_POINT_TYPE = 0;
    /**
     * 价格价格设置类型
     */
    public final static int PRICE_TYPE_PRICE_TYPE = 1;
    /**
     * 等待成交
     */
    public final static int ORDER_TYPE_STATUS1 = 1;
    /**
     * 已成交
     */
    public final static int ORDER_TYPE_STATUS2 = 2;
    /**
     * 等待批单
     */
    public final static int ORDER_TYPE_STATUS3 = 3;
    /**
     * 用户取消
     */
    public final static int ORDER_TYPE_STATUS4 = 4;
    /**
     * 批单被拒
     */
    public final static int ORDER_TYPE_STATUS5 = 5;
    /**
     * 经理取消
     */
    public final static int ORDER_TYPE_STATUS6 = 6;
    //  0: "产品不可用，若有疑问，请联系相关客服人员",未激活也是此状态，逻辑跟不可用保持一致
    //  1: "产品可交易"
    // 	2: "非交易时段，操作无效",
    // 	3: "参考行情，不提供交易服务",
    // 	4: "产品已过期，不可交易",
    // 	5: "当前产品只可进行平仓操作，若有疑问，请联系相关客服人员",
    public final static int TRADE_STATU_ABLE = 1;
    public final static int TRADE_STATU_ENABLE = 0;
    public final static int TRADE_STATUS_DISABLE = 0;

    /**
     * 下单时传递给jni的类型值，cmd
     */
    public final static int TRADE_STATU_NOT_TIME = 2;
    public final static int TRADE_STATU_REF = 3;
    public final static int TRADE_STATU_OVERDUR = 4;
    public final static int TRADE_STATU_CLOSE = 5;
    /**
     * 行情服务器
     */
    public final static int SERVER_TYPE_QUOTE = 0;
    /**
     * 交易服务器
     */
    public final static int SERVER_TYPE_TRADE = 1;
    /**
     * 行情和交易服务器
     */
    public final static int SERVER_TYPE_BOTH = 2;
    /**
     * 即时新闻
     */
    public final static int NEWS_TYPE_INSTANT_NEWS = 1;

    /**下单时传递给服务器的类型值，orderType*/
    /**
     * 专业评论
     */
    public final static int NEWS_TYPE_PROFESSIONAL = 2;
    /**
     * 财经新闻
     */
    public final static int NEWS_TYPE_FINANCIAL_NEWS = 3;
    /**
     * 即市解盘
     */
    public final static int NEWS_TYPE_REAL_TIME = 4;
    /**
     * 黄金头条
     */
    public final static int NEWS_TYPE_HEADLINES_REVIEWS = 5;
    /**
     * 交易策略
     */
    public final static int NEWS_TYPE_TRADTING_STRATEGIES = 6;
    /**
     * 专题报告
     */
    public final static int NEWS_TYPE_SPECIAL_REPORT = 7;
    /**
     * 金道周评
     */
    public final static int NEWS_TYPE_PM_EVALUATION = 8;
    /**
     * 每日评论
     */
    public final static int NEWS_TYPE_DAYS_EVALUATION = 9;
    /**
     * 我的收藏
     */
    public final static int NEWS_TYPE_COLLECT = 0;
    /**
     * 公共类型
     */
    public final static String BULLENTIN_TYPE = "4";
    /**
     * 全部
     */
    public final static int BULLENTIN_TYPE_ALL = 0;
    /**
     * 我的消息
     */
    public final static int BULLENTIN_TYPE_MY = 99;
    /**
     * 重要公告
     */
    public final static int BULLENTIN_TYPE_IMPORTANT = 1;
    /**
     * 交易时间安排
     */
    public final static int BULLENTIN_TYPE_TRADE_SCHEDULE = 2;
    /**
     * 系统维护公告
     */
    public final static int BULLENTIN_TYPE_MIANTENANCE = 3;
    /**
     * 最新优惠
     */
    public final static int BULLENTIN_TYPE_PREFERENTIAL = 4;
    /**
     * 存取款通知
     */
    public final static int BULLENTIN_TYPE_DEPOSIT_DRAW = 5;
    /**
     * 新闻早班车
     */
    public final static int BULLENTIN_TYPE_NEWS_BUS = 6;
    /**
     * 行情提醒
     */
    public final static int BULLENTIN_TYPE_QUOTE_REMIND = 7;
    /**
     * 服务提示
     */
    public final static int BULLENTIN_TYPE_SERVICE_PROMPT = 8;
    /**
     * 直播间快讯
     */
    public final static int BULLENTIN_TYPE_ON_LIVE = 9;
    /**
     * 网络类型
     */
    public final static int NET_WORK_NO_TYPE = 0;
    public final static int NET_WORK_WIFI_TYPE = 2;
    public final static int NET_WORK_MOBILE_TYPE = 1;
    /**
     * 数据库存储标识类型
     */
    public final static int MARK_NEWS_TYPE = 1;
    public final static int MARK_BULLENTIN_TYPE = 2;
    /**
     * 登录账号类型
     */
    public final static int ACCOUNT_TRADE_TYPE = 0;
    public final static int ACCOUNT_MOBILE_TYPE = 2;//代表手机
    public final static int ACCOUNT_EMAIL_TYPE = 3;//代表邮箱

    public final static String ACCOUNT_COUNTRY_CODE = "81";//默认国家区号 日本
    public final static String ACCOUNT_COUNTRY_ISO = "ISO_3166_392";//默认国家区号 日本
    /**
     * 涨跌幅排序类型
     */
    public final static int SORT_NORMAL_TYPE = 0;
    public final static int SORT_UP_TYPE = 1; //涨幅
    public final static int SORT_DOWN_TYPE = 2; //跌幅
    public final static int SORT_SPEED_TYPE = 3; //速涨
    /**
     * 密码强度
     */
    public final static int PASSWORD_LEVEL_NO = 0;
    public final static int PASSWORD_LEVEL_LOW = 1;
    public final static int PASSWORD_LEVEL_MID = 2;
    public final static int PASSWORD_LEVEL_STRONG = 3;
    public final static String TAB_SELF_QUOTE_TAG = "TAB_SELF_QUOTE_TAG"; //自选列表
    public final static String TAB_SELF2_QUOTE_TAG = "TAB_SELF2_QUOTE_TAG"; //自选涨幅列表
    public final static String TAB_RECOMMEND_QUOTE_TAG = "recommend_tab";//推荐产品列表

    public final static String TAB_NEWS_TAB_TAG = "TAB_NEWS_TAB_TAG";//资讯Tab页
    public final static String TAB_CALENDAR_TAG = "TAB_CALENDAR_TAG";//日历页

    public static final int WARN_INFO = 11; // 添加预警之后,需要刷新数据

    public static String TIME_PORT_PAGE = "1";
    public static String KLINE_PORT_PAGE = "2";
    public static String DETAIL_PORT_PAGE = "3";
    public static String TIME_LAND_PAGE = "4";
    public static String KLINE_LAND_PAGE = "5";
    /**
     * 分享类型，1资讯分享，2广告详情分享，3推荐分享
     */
    public static int SHARE_NEWS_TYPE = 1;
    public static int SHARE_ADS_TYPE = 2;
    public static int SHARE_RECOMMEND_TYPE = 3;
    public static int SHARE_ORDER_TYPE = 4;
    public static int SHARE_OTHER_TYPE = 5;
    public static int SHARE_PROFIT_TYPE = 6;

    public static String ANALYTICS_LOGIN_PAGE = "OtherPage";
    //是否需要统计登录
    public static boolean ANALYTICS_LOGIN_NEED_TRACK = true;


    public static int ORDER_TITLE_POINT = 1;//点数
    public static int ORDER_TITLE_PRICE = 2;//价格


}
