


//允许带负号的浮点数
export function numValidator(rule, value, callback) {
  //验证是否为空
  if (value === undefined || value === null || value === '') {
    callback(new Error('数据不能为空'));
  } else {
    //验证是否符合数字和正负号的格式
    if (!/^-?\d+(\.\d+)?$/.test(value)) {
      callback(new Error('请输入有效的数字'));
    } else {
      const numValue = parseFloat(value);
      // 验证是否为有效数字
      // if (isNaN(numValue)) {
      //   callback(new Error('请输入有效的数字'));
      if (numValue < -9999999999999 || numValue > 9999999999999) {
        // 验证是否在数据范围内
        callback(new Error('数据长度过大!'));
      } else {
        // 如果所有验证都通过，则调用callback无参数
        callback();
      }
    }
  }
}

//允许非空带负号的浮点数
export function numValidatorEnableEmpty(rule, value, callback) {
  if (value && value !== null && value !== '') {
    if (!/^-?\d+(\.\d+)?$/.test(value)) {
      callback(new Error('请输入有效的数字'));
    } else {
      const numValue = parseFloat(value);
      // 验证是否为有效数字
      // if (isNaN(numValue)) {
      //   callback(new Error('请输入有效的数字'));
      if (numValue < -9999999999999 || numValue > 9999999999999) {
        // 验证是否在数据范围内
        callback(new Error('数据长度过大!'));
      } else {
        // 如果所有验证都通过，则调用callback无参数
        callback();
      }
    }
  } else {
    callback();
  }
  // }
}


//只允许自然数
export function numValidatorOnlyNature(rule, value, callback) {
  //验证是否为空
  if (value === undefined || value === null || value === '') {
    callback(new Error('数据不能为空'));
  } else {
    //验证是否符合数字和正负号的格式
    if (!/^\d+$/.test(value)) {
      callback(new Error('请输入自然数'));
    } else {
      const numValue = parseInt(value);
      //验证是否为有效数字
      // if (isNaN(numValue) || numValue < 0) {
      //   callback(new Error('请输入自然数'));
      // }
      if (numValue > 9999999999999) {
        // 验证是否在数据范围内
        callback(new Error('数据长度过大!'));
      } else {
        // 如果所有验证都通过，则调用callback无参数
        callback();
      }
    }
  }
}

//只允许正整数
export function numValidatorNonZeroNature(rule, value, callback) {
  //验证是否为空
  if (value === undefined || value === null || value === '') {
    callback(new Error('数据不能为空'));
  } else {
    //验证是否符合数字和正负号的格式
    if (!/^\d+$/.test(value)) {
      callback(new Error('请输入正整数'));
    } else {
      const numValue = parseInt(value);
      // 验证是否为有效数字
      // if (isNaN(numValue)) {
      //   callback(new Error('请输入有效的数字'));
      if (isNaN(numValue) || numValue <= 0) {
        callback(new Error('请输入正整数'));
      } else if (numValue > 999999999999) {
        // 验证是否在数据范围内
        callback(new Error('数据长度过大!'));
      } else {
        // 如果所有验证都通过，则调用callback无参数
        callback();
      }
    }
  }
}

//只允许正数
export function numValidatorOnlyPositive(rule, value, callback) {
  //验证是否为空
  if (value === undefined || value === null || value === '') {
    callback(new Error('数据不能为空'));
  } else {
    //验证是否符合数字和正负号的格式
    if (!/^\d+(\.\d+)?$/.test(value)) {
      callback(new Error('请输入正数'));
    } else {
      const numValue = parseFloat(value);
      // 验证是否为有效数字
      // if (isNaN(numValue)) {
      //   callback(new Error('请输入有效的数字'));
      if (numValue < -9999999999999 || numValue > 9999999999999) {
        // 验证是否在数据范围内
        callback(new Error('数据长度过大!'));
      } else {
        // 如果所有验证都通过，则调用callback无参数
        callback();
      }
    }
  }
}

//正百分数
export function numValidatorPercentage(rule, value, callback) {
  //验证是否为空
  if (value === undefined || value === null || value === '') {
    callback(new Error('数据不能为空'));
  } else {
    //验证是否符合数字和正负号的格式
    if (!/^\d+(\.\d+)?$/.test(value)) {
      callback(new Error('请输入有效的数字'));
    } else {
      const numValue = parseFloat(value);
      // 验证是否为有效数字
      // if (isNaN(numValue)) {
      //   callback(new Error('请输入有效的数字'));
      if (numValue < 0 || numValue > 100) {
        // 验证是否在数据范围内
        callback(new Error('请输入0-100内的数字!'));
      } else {
        // 如果所有验证都通过，则调用callback无参数
        callback();
      }
    }
  }
}

//允许负百分数
export function numValidatorPercentageNegative(rule, value, callback) {
  //验证是否为空
  if (value === undefined || value === null || value === '') {
    callback(new Error('数据不能为空'));
  } else {
    //验证是否符合数字和正负号的格式
    if (!/^-?\d+(\.\d+)?$/.test(value)) {
      callback(new Error('请输入有效的数字'));
    } else {
      const numValue = parseFloat(value);
      // 验证是否为有效数字
      // if (isNaN(numValue)) {
      //   callback(new Error('请输入有效的数字'));
      if (numValue <= -9999 || numValue >= 9999) {
        // 验证是否在数据范围内
        callback(new Error('数据过长!'));
      } else {
        // 如果所有验证都通过，则调用callback无参数
        callback();
      }
    }
  }
}



