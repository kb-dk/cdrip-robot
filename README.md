# cdrip-robot
cdrip robot simulator

The NSM_Satellite (N30020-AK) cd rip robot accepts a deck of magazines loaded with CD's and
a jobfile_XXXX.job.xml describing the contents of the magazines.  The file is on the form:

<JOBLIST>
<CHARGELIST>

<MAGAZINE num="1">
<PACKAGEID>06000000293755</PACKAGEID>
<MAGAZINESLOT num="1">
<CDNAME></CDNAME>
<JUKEBOXSLOT num="101"/>
</MAGAZINESLOT>
<MAGAZINESLOT num="2">
<CDNAME></CDNAME>
<JUKEBOXSLOT num="102"/>
</MAGAZINESLOT>
...
</MAGAZINE>

</CHARGELIST>
<BLOCK>
<OUTPUT>
<NAME format="BWFTMP">207919/track.wav</NAME>
<INPUT format="CD" mode="auto">
<NAME>101</NAME>
</INPUT>
</OUTPUT>
</BLOCK>
<BLOCK>
<OUTPUT>
<NAME format="BWFTMP">207920/track.wav</NAME>
<INPUT format="CD" mode="auto">
<NAME>102</NAME>
</INPUT>
</OUTPUT>
</BLOCK>
...
</JOBLIST>


Output is the following files (the timestamps must be identical and unique):

* CD-Inspector_YYYY-MM-DD_HH-MM.log which at least contains a line containing the name of the job file: 
`10/21   15:00   C:/Users/cti/Desktop/jobfile_618.job.xml loaded successful`
            
* Result_YYYY-MM-DD_HH-MM.log which contains ##TODO##
 
The timestamp + embedded file name in the log line allows us to trace back to the original job, which 
in turn allow us to identify which Natbibnumber (the <NAME ...>2079212/track.wav</NAME> part corresponds
to Natbibnumber 2079212) each magazine slot corresponds to.


This project contains a very simple simulator allowing us to test functionality without access
to the real robot.

/tra 2015-04-20


