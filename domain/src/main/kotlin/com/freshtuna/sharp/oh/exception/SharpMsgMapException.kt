package com.freshtuna.sharp.oh.exception

import com.freshtuna.sharp.oh.Oh

class SharpMsgMapException(oh: Oh, val map: Map<String, String>) : SharpException(oh)
