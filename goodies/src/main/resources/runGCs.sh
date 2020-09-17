#!/bin/bash
set -e
time java --enable-preview -Xms4G -Xmx8G -XX:StartFlightRecording=disk=true,dumponexit=true,filename=G1.jfr,maxsize=1024m,settings=profile org.przybyl.ddj15.lowPauseGCs.GCQuasiBenchmark
time java --enable-preview -XX:+UseParallelGC -Xms4G -Xmx8G -XX:StartFlightRecording=disk=true,dumponexit=true,filename=ParallelOld.jfr,maxsize=1024m,settings=profile org.przybyl.ddj15.lowPauseGCs.GCQuasiBenchmark
# oops, removed in 14 ;-)
# time java --enable-preview -XX:+UseConcMarkSweepGC -Xms4G -Xmx8G -XX:StartFlightRecording=disk=true,dumponexit=true,filename=CMS.jfr,maxsize=1024m,settings=profile org.przybyl.ddj15.lowPauseGCs.GCQuasiBenchmark
time java --enable-preview -XX:+UseZGC -Xms4G -Xmx8G -XX:StartFlightRecording=disk=true,dumponexit=true,filename=ZGC.jfr,maxsize=1024m,settings=profile org.przybyl.ddj15.lowPauseGCs.GCQuasiBenchmark
time java --enable-preview -XX:+UseShenandoahGC -Xms4G -Xmx8G -XX:StartFlightRecording=disk=true,dumponexit=true,filename=Shenandoah.jfr,maxsize=1024m,settings=profile org.przybyl.ddj15.lowPauseGCs.GCQuasiBenchmark