#test insert of an experiment in new GenomeRNAi submission REST service

use strict;
use warnings;
#use JSON;
#use LWP;

my $browser;
my $json;
#my $browser = LWP::UserAgent->new;
#my $json = JSON->new;

my %data_to_json;
my $section;
my @lineNum = (0, 0, 0);  #stores current line number of section
my @colToHsh;

while (my $line = <DATA>) {  
   if($line=~ m/\[sheet\s(\d+)\]/) {
	$section = $1;       
        next;
   }   
   $line =~ s/\n//g;
   my @lineSplit = split("\t", $line);
   $lineNum[$section]++;  # count the current line
   if($section eq 1) {     
       $data_to_json{"screen data"}{$lineSplit[0]} = $lineSplit[1];  
   }
   if($section eq 2) {
       my @vals = split("\t", $line);
       if($lineNum[$section] == 1) {  #if we are in the header row, store header to column relationship
	    for(my $i = 0; $i < $#vals; $i++) {
                $colToHsh[$i] = $vals[$i];
	    }
       }
       else {
            for(my $i = 0; $i < $#vals; $i++) {
                push @{$data_to_json{"phenotype data"}{$lineNum[$section]}},($colToHsh[$i]=>$vals[$i]); 
	    }
       }  
   }
   if($section eq 3) {
    # todo  
   }
}
my $hello;
#my $encoded = $json->encode(\%data_to_json);

# attempting to store screen data in the experiment
#my $uri = "http://localhost:8080/GenomeRNAiSubmission/contributorExperiment/rest";
#my $req = HTTP::Request->new( 'POST', $uri );
#$req->content_type('application/json');
#$req->content($json->encode($data_to_json{"screen data"}));
#my $ua = LWP::UserAgent->new; # You might want some options here
#my $res = $ua->request($req);
#my $contrib_obj = $json->decode($res->decoded_content);
##retrive the database id from the experiment we just imported
#my $db_id = $contrib_obj->{"id"};




#for debugging
#print $json->pretty->encode($data_to_json{"screen data"})
#print $json->pretty->encode(\%data_to_json);


__DATA__
[sheet 1]
screenTitle	Sindbis virus (SINV) infection
publicationTitle	Genome-wide RNAi Screen Identifies SEC61A and VCP as Conserved Regulators of Sindbis Virus Entry
authors	Panda et al.
publicationYear	2013
pubmedId	24332855
orgId	1
screenType	Cell-based
bioSource	Cell line
assay	Sindbis virus (SINV) GFP protein expression
assayMethod	Fluorescence
reagentType	dsRNA
scope	Genome-wide
scoreType	Z-score
scoreCutoff	< -2 OR > 2
notes	Primary screen performed in duplicate. Z-scores from replicate B and from secondary validation screen are shown in the comment field. Recombinant SINV (HRsp) expressing GFP was used for infection. 
notesToTheCurator	
	 							
Notes to the Curators	
[sheet 2]
activityR1Ch1	activityR1Ch2	activityR2Ch1	activityR2Ch2	bioModel	contributorExperiment	finalWellAnno	image	library	libraryProvider	normalizedR1	normalizedR2	phenotype	plate	position	primaryGeneID	rawR1Ch1	rawR1Ch2	rawR2Ch1	rawR2Ch2	reagentId	reagentName	score	well	wellAnno	myGeneId2	myGenesymbols123
1.13	0.955	0.164	0.121	DL1	1	pos		Dharmakkan		-3.385	-3.444	healthy	1	26	NA	2020	1500	1740	1490	1	DMK1221	1	B02	pos	NA	blablub
1.21	1.12	0.191	0.158	DL1	1	pos		Dharmakkan		-3.254	-3.28	dead	3	26	NA	580	1780	390	1380	2	DMK1222	123	B02	pos	NA	asdf
1.08	0.912	0.164	0.139	DL1	1	pos		Dharmakkan		-3.311	-3.18	vital	2	26	NA	1970	1400	1560	1650	3	DMK1223	123.321	B02	pos	NA	asd
0.938	1.08	0.157	0.228	DL1	1	pos		Dharmakkan		-3.173	-2.711	healthy	3	25	NA	450	1730	320	1990	4	DMK1224	1	B01	pos	NA	dc
1.09	0.964	0.238	0.179	DL1	1	pos		Dharmakkan		-2.785	-2.892	dead	2	25	NA	1990	1480	2270	2130	5	DMK1225	123.321	B01	pos	NA	asd
1.01	1.22	0.179	0.303	DL1	1	pos		Dharmakkan		-3.092	-2.469	vital	1	25	NA	1800	1910	1900	3730	6	DMK1226	2	B01	pos	NA	asdasd
0.663	0.745	0.197	0.184	DL1	1	sample		Dharmakkan		-2.345	-2.478	healthy	1	310	HFA00350	1180	1170	2090	2270	7	DMK1227	231	M22	sample	CG12676	asdas
1.46	1.22	0.373	0.433	DL1	1	sample		Dharmakkan		-2.564	-1.963	dead	2	205	HFA03319	2670	1880	3550	5150	8	DMK1228	123.321	I13	sample	CG5838	aca
0.639	0.958	0.176	0.355	DL1	1	sample		Dharmakkan		-2.453	-1.892	vital	2	86	HFA02050	1170	1470	1680	4230	9	DMK1229	123	D14	sample	CG10383	dsad
0.583	0.777	0.172	0.281	DL1	1	sample		Dharmakkan		-2.36	-1.931	healthy	3	165	HFA03722	280	1240	350	2450	10	DMK1230	123.321	G21	sample	CG2225	blablub
0.5	0.627	0.162	0.22	DL1	1	sample		Dharmakkan		-2.222	-1.972	dead	3	287	HFA04382	240	1000	330	1920	11	DMK1231	31	L23	sample	CG3511	asdf
0.542	0.608	0.176	0.216	DL1	1	sample		Dharmakkan		-2.212	-1.959	vital	3	45	HFA04696	260	970	360	1880	12	DMK1232	123.321	B21	sample	CG5575	asd
1.08	0.947	0.495	0.26	DL1	1	sample		Dharmakkan		-1.724	-2.325	healthy	3	168	HFA04410	520	1510	1010	2270	13	DMK1233	123.321	G24	sample	CG3732	dc
1.49	1.21	0.423	0.569	DL1	1	sample		Dharmakkan		-2.413	-1.545	dead	2	207	HFA03496	2730	1850	4030	6770	14	DMK1234	31	I15	sample	CG4722	asd
1.63	1.68	0.577	0.662	DL1	1	sample		Dharmakkan		-2.091	-1.806	vital	2	6	HFA02927	2980	2580	5500	7880	15	DMK1235	123.321	A06	sample	CG6094	asdasd
0.562	0.614	0.181	0.268	DL1	1	sample		Dharmakkan		-2.227	-1.658	healthy	3	20	HFA04297	270	980	370	2340	16	DMK1236	123.321	A20	sample	CG17922	asdas
0.542	0.702	0.196	0.288	DL1	1	sample		Dharmakkan		-2.06	-1.749	dead	3	21	HFA03201	260	1120	400	2510	17	DMK1237	123.321	A21	sample	CG9324	aca
0.0984	0.456	0.0588	0.195	DL1	1	sample		Dharmakkan		NA	-1.688	vital	2	39	HFA01868	180	700	560	2320	18	DMK1238	123	B15	sample	CG9239	dsad
0.815	1.11	0.287	0.548	DL1	1	sample		Dharmakkan		-2.102	-1.479	healthy	1	295	HFA00683	1450	1740	3040	6750	19	DMK1239	123.321	M07	sample	CG5397	blablub
1.08	0.971	0.487	0.389	DL1	1	sample		Dharmakkan		-1.739	-1.781	dead	2	187	HFA01972	1970	1490	4640	4630	20	DMK1240	123.321	H19	sample	CG31732	asdf
0.292	0.458	0.451	0.204	DL1	1	sample		Dharmakkan		NA	-1.627	vital	3	39	HFA04660	140	730	920	1780	21	DMK1241	2.312	B15	sample	CG10327	asd
0.497	0.821	0.2	0.403	DL1	1	sample		Dharmakkan		-1.905	-1.491	healthy	2	331	HFA02250	910	1260	1910	4790	22	DMK1242	23	N19	sample	CG31991	dc
1.12	0.997	0.485	0.463	DL1	1	sample		Dharmakkan		-1.807	-1.568	dead	3	58	HFA04642	540	1590	990	4040	23	DMK1243	123.321	C10	sample	CG3245	asd
0.854	0.853	0.24	0.6	DL1	1	sample		Dharmakkan		-2.424	-0.97	vital	3	13	HFA03198	410	1360	490	5230	24	DMK1244	123.321	A13	sample	CG9319	asdasd
0.59	0.873	0.271	0.395	DL1	1	sample		Dharmakkan		-1.714	-1.605	healthy	1	332	HFA03065	1050	1370	2880	4870	25	DMK1245	123.321	N20	sample	CG7627	asdas
0.754	0.853	0.296	0.447	DL1	1	sample		Dharmakkan		-1.944	-1.395	dead	2	262	HFA02892	1380	1310	2820	5320	26	DMK1246	123.321	K22	sample	CG5780	aca
0.854	1.02	0.333	0.542	DL1	1	sample		Dharmakkan		-1.952	-1.367	vital	3	22	HFA04619	410	1620	680	4730	27	DMK1247	123	A22	sample	CG17950	dsad
0.438	0.483	0.196	0.26	DL1	1	sample		Dharmakkan		-1.752	-1.353	healthy	3	4	HFA04054	210	770	400	2270	28	DMK1248	1	A04	sample	CG10082	blablub
0.466	0.745	0.219	0.397	DL1	1	sample		Dharmakkan		-1.687	-1.371	dead	1	83	HFA03119	830	1170	2320	4890	29	DMK1249	123.321	D11	sample	CG8890	asdf
0.729	0.476	0.348	0.255	DL1	1	sample		Dharmakkan		-1.661	-1.367	vital	3	10	HFA04580	350	760	710	2220	30	DMK1250	123.321	A10	sample	CG9865	asd
0.438	0.558	0.221	0.291	DL1	1	sample		Dharmakkan		-1.582	-1.4	healthy	3	38	HFA04317	210	890	450	2540	31	DMK1251	231	B14	sample	CG2803	dc
0.292	0.471	0.246	0.251	DL1	1	sample		Dharmakkan		NA	-1.373	dead	1	352	HFA00595	520	740	2610	3090	32	DMK1252	23	O16	sample	CG3338	asd
0.667	0.997	0.324	0.563	DL1	1	sample		Dharmakkan		-1.637	-1.287	vital	3	116	HFA04367	320	1590	660	4910	33	DMK1253	123.321	E20	sample	CG3413	asdasd
0.25	0.671	0.113	0.365	DL1	1	sample		Dharmakkan		NA	-1.342	healthy	3	94	NA	120	1070	230	3180	34	DMK1254	123	D22	sample	NA	asdas
1.21	1.16	0.615	0.658	DL1	1	sample		Dharmakkan		-1.568	-1.28	dead	2	87	HFA03124	2210	1780	5860	7830	35	DMK1255	123.321	D15	sample	CG8954	aca
0.852	0.99	0.444	0.568	DL1	1	sample		Dharmakkan		-1.536	-1.264	vital	2	189	HFA03537	1560	1520	4230	6760	36	DMK1256	123	H21	sample	CG11861	dsad
3.02	1.55	1.56	0.894	DL1	1	sample		Dharmakkan		-1.544	-1.254	healthy	3	3	HFA02618	1450	2470	3190	7800	37	DMK1257	123.321	A03	sample	CG31678	blablub
0.896	0.966	0.549	0.485	DL1	1	sample		Dharmakkan		-1.301	-1.456	dead	3	383	HFA04284	430	1540	1120	4230	38	DMK1258	12	P23	sample	CG16896	asdf
1.01	0.936	0.549	0.528	DL1	1	sample		Dharmakkan		-1.466	-1.288	vital	1	68	HFA00549	1790	1470	5830	6510	39	DMK1259	123.321	C20	sample	CG2903	asd
[sheet 3]

