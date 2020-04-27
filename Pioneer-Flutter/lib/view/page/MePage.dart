import 'package:flutter/material.dart';

class MePage extends StatefulWidget{
  @override
  State<StatefulWidget> createState() {
    return MeState();
  }
}

class MeState extends State<MePage>{
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
        color: Colors.yellow,
        child: Center(
          child: Text('this is MePage.'),
      ),
    );
  }
}