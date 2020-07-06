/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React from 'react';
import {
  StyleSheet,
  Text,
  View,
} from 'react-native';

const App = () => {
  return (
    <View style={styles.content}>
      <Text>Hello World</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  content: {
    flex:1,
    justifyContent:"center",
    alignItems:"center"
  }
});

export default App;
