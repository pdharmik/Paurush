/*
	Contributed by Carlos Nunes, cmanunes@gmail.com, 26.01.2008
*/

var text = new Array();

text['today'] = 'Idag';
text['time'] = 'Hora';

text['dayNamesShort'] = new Array(
'Sön',
'Mån',
'Tis',
'Ons',
'Tor',
'Fre',
'Lör'
);
text['dayNames'] = new Array(
'Söndag',
'Måndag',
'Tisdag',
'Onsdag',
'Torsdag',
'Fredag',
'Lördag'
);

text['monthNamesShort'] = new Array(
'Jan',
'Feb',
'Mar',
'Apr',
'Maj',
'Jun',
'Jul',
'Aug',
'Sep',
'Oct',
'Nov',
'Dec'
);

text['monthNames'] = new Array(
'Januari',
'Februari',
'Mars',
'April',
'Maj',
'Juni',
'Juli',
'Augusti',
'September',
'Oktober',
'November',
'December'
);

//%m = month; %d = day; %Y = year
text['footerDateFormat'] = '%Y-%m-%d',
text['dateFormat'] = '%Y-%m-%d',
text['footerDefaultText'] = 'Select date Swedish',

text['clear'] = 'Limpar Data',
text['prev_year'] = 'Ano anterior',
text['prev_month'] = 'Mês anterior',
text['next_month'] = 'Próximo mês',
text['next_year'] = 'Próximo ano',
text['close'] = 'Stäng',
text['startDay'] = '1',


// weekend days (0 - sunday, ... 6 - saturday)
text['weekend'] = "0,6";
text['make_first'] = "Começa com %s";

//select and cancel
text['select'] = "Select";
text['cancel'] = "Cancel";

RichCalendar.rc_lang_data['sv'] = text;
