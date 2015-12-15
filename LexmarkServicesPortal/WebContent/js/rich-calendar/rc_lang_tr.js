﻿/*
	Contributed by Carlos Nunes, cmanunes@gmail.com, 26.01.2008
*/

var text = new Array();

text['today'] = 'bugün';
text['time'] = 'Hora';

text['dayNamesShort'] = new Array(
'Pz',
'Pt',
'Sa',
'Ça',
'Pe',
'Cu',
'Ct'
);
text['dayNames'] = new Array(
'Pazar',
'Pazartesi',
'Sali',
'Çarsamba',
'Persembe',
'Cuma',
'Cumartesi'
);

text['monthNamesShort'] = new Array(
'Oca',
'Sub',
'Mar',
'Nis',
'May',
'Haz',
'Tem',
'Agu',
'Eyl',
'Eki',
'Kas',
'Ara'
);

text['monthNames'] = new Array(
'Ocak',
'Subat',
'Mart',
'Nisan',
'Mayis',
'Haziran',
'Temmuz',
'Agustos',
'Eylül',
'Ekim',
'Kasim',
'Aralik'
);

//%n = month; %j = day; %Y = year
text['footerDateFormat'] = '%j.%n.%Y',
text['dateFormat'] = '%j.%n.%Y',
text['footerDefaultText'] = 'Select date Turkish',

text['clear'] = 'Limpar Data',
text['prev_year'] = 'Ano anterior',
text['prev_month'] = 'Mês anterior',
text['next_month'] = 'Próximo mês',
text['next_year'] = 'Próximo ano',
text['close'] = 'kapat',
text['startDay'] = '1',


// weekend days (0 - sunday, ... 6 - saturday)
text['weekend'] = "0,6";
text['make_first'] = "Começa com %s";


RichCalendar.rc_lang_data['tr'] = text;
