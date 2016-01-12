<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Vikiprojekts:Latvijas pagasti | LGIA dati">

<h3><a href="https://lv.wikipedia.org/wiki/Vikiprojekts:Latvijas_pagasti">Vikiprojekts:Latvijas pagasti</a></h3>
<p class="explain">Rîks Vikipçdijas aizmetòa izveidei par apdzîvotu vietu no LGIA vietvârdu datubâzes datiem.</p>

<div class="form-container">
<form class="form-inline" action="/lgia-extract" method="GET">
<div class="form-group">
    <label for="id"><a href="http://vietvardi.lgia.gov.lv/vv/to_www.sakt">LGIA ID</a></label>
	<input class="form-control" type="text" id="id" name="id" value="<#if id??>${id}</#if>">    
</div>
<button class="btn btn-default" type="submit">Iesniegt</button>
</form>
</div>

<#if message??>
	<div class="message">${message}</div>
</#if>

<#if id??>
<ul class="links">
	<li><a href="${url}">LGIA</a></li>
	<li><a href="https://www.google.lv/maps/place/${name},+${parish}">Google Maps (ciems)</a></li>
	<li><a href="https://www.google.lv/maps/place/${parish}">Google Maps (pagasts)</a></li>
	<li><a href="http://www.kurtuesi.lv/maps">Kurtuesi</a> (augstums no topokartes)</li>
	<li><a href="http://balticmaps.eu/">Baltic Maps</a></li>	
</ul>


<!-- TODO copy to clipboard -->

<pre class="wikitext">
{{Apdzîvotas vietas infokaste
| name                     = ${name}
| settlement_type          = ${type}
| image_skyline            = 
| image_caption            = 
| pushpin_map              = Latvia
| pushpin_label_position   = right
| subdivision_type         = Valsts
| subdivision_type1        = Novads
| subdivision_type2        = Pagasts
| subdivision_name         = {{LAT}}
| subdivision_name1        = [[${municipality}]]
| subdivision_name2        = [[${parish}]]
| established_title        = &lt;!-- Pirmoreiz minçts --&gt;
| established_date         = &lt;!-- datums, kad pirmoreiz minçts --&gt;
| area_total_km2           = 
| area_land_km2            = &lt;!-- var ielikt teritoriâlplânoðanâ noteikto platîbu --&gt;
| population_as_of         = ${populationYear}
| population_footnotes     = &lt;ref&gt;{{Tîmekïa atsauce |url=${url} |title=Informâcija par objektu: ${name} |accessdate=${date?string('YYYY-MM-DD')} |work= LÌIA vietvârdu datubâze |publisher=[[Latvijas Ìeotelpiskâs informâcijas aìentûra]] |language= lv }}&lt;/ref&gt;
| population_total         = ${population}
| population_density_km2   = <!-- apdzîvotîbas blîvums -->
| latd  = ${lat_deg} | latm  = ${lat_min} | lats  = ${lat_sec} | latNS  = N
| longd = ${lon_deg} | longm = ${lon_min} | longs = ${lon_sec} | longEW = E
| elevation_m              = 
| website                  = &lt;!-- mâjaslapa; ja atðíirîga no pagasta/novada lapas --&gt;
| postal_code_type         = Pasta nodaïa
| postal_code              = 
| footnotes                = &lt;!-- specpiezîmes --&gt;
}}

'''${name}''' ir ciems [[${municipality}|${municipality_gen}]] [[${parish}|${parish_loc}]]. Izvietojies pagasta &lt;!-- izvçlies vienu --&gt; ziemeïu dienvidu austrumu rietumu centrâlajâ daïâ {{nobr|XX km}} no novada centra [[XXXX]]s un {{nobr|YYY km}} no [[Rîga]]s.

Apdzîvotâ vieta izvietojusies pie autoceïa [[PYYYY]], ZZZZ upes krastâ.

Pirms 2009. gadâ noslçgtâs [[Latvijas administratîvais iedalîjums|administratîvi teritoriâlâs reformas]] atradâs [[${district}|${district_loc}]].

== Atsauces ==
{{atsauces}}

{{Latvijas ìeogrâfija-aizmetnis}}

{{${parish_gen} ciemi}}
</pre>	
</#if>
</@layout.masterTemplate>