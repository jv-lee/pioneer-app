import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class MeContent extends StatefulWidget{
  @override
  State<StatefulWidget> createState() {
    return MeContentState();
  }

}

class MeContentState extends State<MeContent>{
  @override
  Widget build(BuildContext context) {
    return Container(color:Theme.of(context).backgroundColor ,);
  }

}