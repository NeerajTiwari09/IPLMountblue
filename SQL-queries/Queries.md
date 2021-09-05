## MySQL Queries:-
1. Number of matches played per year of all the years in IPL:-
>SELECT season, COUNT(season) AS frequency FROM matches GROUP BY season ORDER BY season;

2. Number of matches won of all teams over all the years of IPL:-
>SELECT winner, COUNT(winner) AS frequency FROM matches WHERE result != "no result" GROUP BY winner;

3. For the year 2016 get the extra runs conceded per team:-
>SELECT deliveries.batting_team, SUM(deliveries.extra_runs) FROM deliveries
 INNER JOIN matches ON matches.id = deliveries.match_id WHERE season = 2016 GROUP BY batting_team;

4. For the year 2015 get the top economical bowlers:-
>SELECT deliveries.bowler, (SUM(deliveries.total_runs) / COUNT(deliveries.ball)) * 6 AS economicalRate FROM deliveries
INNER JOIN matches ON matches.id = deliveries.match_id WHERE season = 2015 GROUP BY bowler ORDER BY economicalRate;

5. Create your own scenario:-
    1. Name of venues in 2017:-
    >SELECT venue FROM matches WHERE season = 2017 GROUP BY venue;


## PostgreSQL Quries:-
1. Number of matches played per year of all the years in IPL:-
>SELECT season, COUNT(season) AS frequency FROM matches GROUP BY season ORDER BY season;

2. Number of matches won of all teams over all the years of IPL:-
>SELECT winner, COUNT(winner) AS frequency FROM matches WHERE matches.result != 'no result' GROUP BY winner ;

3. For the year 2016 get the extra runs conceded per team:-
>SELECT deliveries.batting_team, sum(deliveries.extra_runs) AS totalExtraRun
FROM deliveries INNER JOIN matches ON matches.id = deliveries.match_id WHERE season = 2016 GROUP BY batting_team;

4. For the year 2015 get the top economical bowlers:-
>SELECT deliveries.bowler, (CAST(sum(deliveries.total_runs) as decimal) / COUNT(deliveries.ball)) * 6 AS economicalRate FROM deliveries INNER JOIN matches ON matches.id = deliveries.match_id WHERE season = 2015 GROUP BY bowler ORDER BY economicalRate;

5. Create your own scenario:-
    1. Name of venues in 2017:-
    >SELECT venue FROM matches WHERE season = 2017 GROUP BY venue;