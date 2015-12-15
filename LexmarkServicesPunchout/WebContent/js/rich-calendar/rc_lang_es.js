/*
	Contributed by Carlos Nunes, cmanunes@gmail.com, 26.01.2008
*/

var text = new Array();

text['today'] = 'Hoy';
text['time'] = 'Hora';

text['dayNamesShort'] = new Array(
'Dom',
'Lun',
'Mar',
'Mi&eacute;',
'Jue',
'Vie',
'S&aacute;b'
);
text['dayNames'] = new Array(
'Domingo',
'Lunes',
'Martes',
'Mi&eacute;rcoles',
'Jueves',
'Viernes',
'S&aacute;bado'
);

text['monthNamesShort'] = new Array(
'Ene',
'Feb',
'Mar',
'Abr',
'May',
'Jun',
'Jul',
'Ago',
'Sep',
'Oct',
'Nov',
'Dic'
);

text['monthNames'] = new Array(
'Enero',
'Febrero',
'Marzo',
'Abril',
'Mayo',
'Junio',
'Julio',
'Agosto',
'Septiembre',
'Octubre',
'Noviembre',
'Diciembre'
);

//%n = month; %j = day; %Y = year
text['footerDateFormat'] = '%j/%n/%Y',
text['dateFormat'] = '%j/%n/%Y',
text['footerDefaultText'] = 'Seleccione Date',

text['clear'] = 'Clear Spanish',
text['prev_year'] = 'Ano anterior',
text['prev_month'] = 'Mês anterior',
text['next_month'] = 'Próximo mês',
text['next_year'] = 'Próximo ano',
text['close'] = 'Cerrar',
text['startDay'] = '0',


// weekend days (0 - sunday, ... 6 - saturday)
text['weekend'] = "0,6";
text['make_first'] = "Começa com %s";


RichCalendar.rc_lang_data['es'] = text;
