import 'package:flutter/material.dart';
import 'package:pioneer_flutter/constants/http_constants.dart';
import 'package:pioneer_flutter/model/content_entity.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';

/// @author jv.lee
/// @date 2020/5/19
/// @description
class GirlItem extends StatefulWidget {
  GirlItem(this.data) : super();
  final ContentData data;

  @override
  _GirlItemState createState() => _GirlItemState();
}

class _GirlItemState extends State<GirlItem> {
  @override
  Widget build(BuildContext context) {
    return Card(
      margin: EdgeInsets.fromLTRB(30, 15, 30, 15),
      elevation: 10,
      child: Container(
        height: ThemeDimens.item_girl_root_height,
        child: Column(
          children: <Widget>[
            Container(
              height: ThemeDimens.item_girl_picture_height,
              decoration: BoxDecoration(
                  color: Theme.of(context).primaryColor,
                  borderRadius: BorderRadius.only(
                    topLeft: Radius.circular(
                        ThemeDimens.item_content_picture_radius),
                    topRight: Radius.circular(
                        ThemeDimens.item_content_picture_radius),
                  ),
                  image: DecorationImage(
                      image: NetworkImage(HttpConstants.getCropImagePath(
                          widget.data.images[0])),
                      fit: BoxFit.cover)),
            ),
            Container(
              alignment: Alignment.centerLeft,
              padding: EdgeInsets.all(ThemeDimens.padding_large),
              decoration: BoxDecoration(
                  color: Theme.of(context).canvasColor,
                  borderRadius: BorderRadius.only(
                    bottomLeft: Radius.circular(
                        ThemeDimens.item_content_picture_radius),
                    bottomRight: Radius.circular(
                        ThemeDimens.item_content_picture_radius),
                  )),
              height: ThemeDimens.item_girl_des_height,
              child: Text(
                widget.data.desc,
                maxLines: 3,
                overflow: TextOverflow.ellipsis,
                style: TextStyle(
                    fontSize: ThemeDimens.font_size_small,
                    color: Theme.of(context).primaryColor),
              ),
            )
          ],
        ),
      ),
    );
  }
}
