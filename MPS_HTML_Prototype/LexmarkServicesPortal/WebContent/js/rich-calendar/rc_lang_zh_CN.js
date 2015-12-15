var text = new Array();

text['today'] = '今天';
text['time'] = '时间';

text['dayNamesShort'] = new Array(
'周日',
'周一',
'周二',
'周三',
'周四',
'周五',
'周六'
);
text['dayNames'] = new Array(
'星期日',
'星期一',
'星期二',
'星期三',
'星期四',
'星期五',
'星期六'
);

text['monthNamesShort'] = new Array(
'一月',
'二月',
'三月',
'四月',
'五月',
'六月',
'七月',
'八月',
'九月',
'十月',
'十一月',
'十二月'
);

text['monthNames'] = new Array(
'一月',
'二月',
'三月',
'四月',
'五月',
'六月',
'七月',
'八月',
'九月',
'十月',
'十一月',
'十二月'
);

//%n = month; %j = day; %Y = year
text['footerDateFormat'] = '%n/%j/%Y',
text['dateFormat'] = '%n/%j/%Y',
text['footerDefaultText'] = '选择日期',

text['clear'] = '清除日期',
text['prev_year'] = '上一年',
text['prev_month'] = '上一月',
text['next_month'] = '下一月',
text['next_year'] = '下一年',
text['close'] = '关闭',


// weekend days (0 - sunday, ... 6 - saturday)
text['weekend'] = "0,6";
text['make_first'] = "开始于 %s";
text['startDay'] = 1;


RichCalendar.rc_lang_data['zh_CN'] = text;
