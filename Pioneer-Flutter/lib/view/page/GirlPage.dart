import 'package:flutter/material.dart';

class GirlPage extends StatefulWidget{
  @override
  State<StatefulWidget> createState() {
    return GirlState();
  }
}

class GirlState extends State<GirlPage>{
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
        color: Colors.blue,
        child: Center(
          child: Text('this is GirlPage.'),
      ),
    );
  }
}