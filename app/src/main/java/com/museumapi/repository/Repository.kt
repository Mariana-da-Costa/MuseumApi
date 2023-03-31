package com.museumapi.repository

import android.util.Log
import com.museumapi.model.Department
import com.museumapi.model.MuseumObject
import com.museumapi.network.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

const val TAG = "Repository"

class Repository () {
    val service = RetrofitInstance.api
    val objectIDs = listOf(285177,260358,288149,288118,13877,208853,202780,505170,197478,207005,
        20498,694642,674,234881,281867,258464,201952,206554,199706,199707,199705,199313,196910,
        209329,13471,14931,196439,270265,256571,207397,13084,70582,467746,447568,209080,236107,
        631000,21667,196497,196452,196467,196462,196446,196464,20415,20499,19777,19780,19776,
        197995,190607,193998,892133,191777,207700,247931,205536,204326,197732,197729,197730,197731,
        203826,254178,254871,200662,3667,202291,208062,254869,469960,11055,226956,195189,207260,
        204858,203938,203947,207025,206585,205182,203935,195180,207007,199036,205518,254865,194295,
        247925,196341,231011,246585,189732,195190,207024,205438,205439,469924,469916,198570,198659,
        205226,198750,202242,203886,4614,4615,8017,8452,5315,452845,207813,488486,547202,466311,
        208816,503546,257603,208149,255391,251929,446646,256126,19881,201562,201570,201522,201577,
        201578,249223,6823,254613,815404,19747,13350,13349,248483,7871,8360,4584,12687,255367,6951,
        6952,544601,21623,19790,497698,590954,232269,288296,204952,253555,251838,253056,466645,
        22275,548360,437658,61429,453243,202225,238552,207253,253557,39676,42183,205078,192701,
        254864,254168,248299,254906,192700,786829,193692,469840,23214,203747,738889,210774,208159,
        238054,247229,247349,254911,469925,27789,201563,824696,254478,242010,8078,252890,20143,
        2310,83972,436532,19082,20191,630,9321,5786,497679,14297,7912,19850,852413,726732,253505,
        715522,715518,9388,450716,254589,206399,251428,486423,459079,464375,251268,547697,447086,
        236688,437283,199390,10946,19781,196447,3208,464454,459182,205422,24648,231893,206697,
        449211,255154,451367,446990,204568,544514,246931,544689,251050,253566,256974,461519,238971,
        253373,25607,204634,435904,41747,247395,460839,437368,253370,505054,547334,241014,544515,
        247926,251493,202752,254213,249222,248268,19909,19907,19908,544826,548343,40765,246992,
        202143,204834,248008,702903,254825,239519,250946,255408,19897,257640,253050,248140,197357,
        464125,436896,254587,250951,451010,252452,749358,749368,202227,459052,255417,241004,547699,
        247967,248891,189164,10497,437812,587759,291956,208320,283736,249228,44516,325688,329077,
        255275,547571,437402,325089,44515,463188,437995,204620,13052,546123,436121,551559,27790,
        544860,467691,544509,253046,204804,72498,551560,552475,32647,503443,452598,247326,22373,
        503951,467648,212215,499559,544498,258424,50349,57378,248689,242041,24861,196498,248892,
        196505,196486,196482,196443,248499,257639,206397,247524,236643,44858,22090,450513,254508,
        196507,75406,75405,75404,22098,196488,196491,206489,196479,196458,196461,196445,196466,
        196474,196451,196472,196463,196481,196473,196469,196453,196459,196440,196448,196475,196503,
        196494,196501,196493,196502,196492,196500,196495,196496,196449,196444,196456,196450,196465,
        196485,196471,196504,196441,196484,256543,196480,196499,232048,200152,211486,107375,196483,
        196468,547820,446151,201371,700240,694157,464490,196454,203648,203650,236031,27966,231592,
        231593,437749,81127,23360,83605,194569,834011,834012,553763,857101,202195,202196,543882,
        450528,247405,39635,452572,242311,204619,326623,253483,61660,544465,246701,249091,232203,
        203842,325511,316009,74906,248500,11934,547768,13897,203649,204010,833990,35952,451894,
        591832,248138,255973,15860,202221,463795,752047,472240,81141,436960,206372,254547,544693,
        201872,591833,547626,451494,548864,543957,543969,543956,547633,547634,490018,546280,547632,
        81105,187900,547551,486839,44152,547629,329074,209168,1530,547567,547630,44898,37942,
        448183,436636,548865,437399,202892,202888,202891,312677,503517,451358,472849,49216,451943,
        547746,207764,209163,209166,209161,209159,209167,209162,209157,209158,39880,209165,209160,
        209156,209164,247000,544464,247001,56277,257580,347925,96434,252581,254468,44763,471428,
        453372,472312,248696,248706,502761,348006,253624,204640,83242,738758,464030,489124,205295,
        543958,23480,23888,31948,203647,453160,199677,23470,23411,23474,257629,548593,461148,
        545393,591843,207699,546282,546281,203655,203656,253487,468561,206419,452066,546805,247004,
        255251,255012,24786,232605,436002)

    private fun getObjectIDs(deptoID: Int = -1): List<Int>? {
        var objectIDs: List<Int>? = null
        CoroutineScope(Dispatchers.IO).launch {
            val response = try {
                if (deptoID == -1)
                    service.fetchMuseumObjectIds()
                else
                    service.fetchMuseumObjectIdsByDepto(deptoID)
            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                objectIDs = response.body()!!.objectIDs
            } else {
                Log.e(TAG, "Response not successful")
            }
        }
        return objectIDs
    }

    fun getMuseumObject(objectID: Int): MuseumObject? {
        var museumObject: MuseumObject? = null
        CoroutineScope(Dispatchers.IO).launch {
            val response = try {
                service.fetchMuseumObject(objectID)
            } catch(e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                return@launch
            }
            if(response.isSuccessful && response.body() != null) {
                museumObject = response.body()!!
            } else {
                Log.e(TAG, "Response not successful")
            }
        }
        return museumObject
    }

    fun getDepartments(): List<Department>? {
        var departmentList: List<Department>? = null
        CoroutineScope(Dispatchers.IO).launch {
            val response = try {
                service.fetchDepartments()
            } catch(e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                return@launch
            }
            if(response.isSuccessful && response.body() != null) {
                departmentList = response.body()!!.departments
            } else {
                Log.e(TAG, "Response not successful")
            }
        }
        return departmentList
    }

    fun getFavorites(): List<MuseumObject> {
        TODO()
    }

    fun getNObjects(n: Int): List<MuseumObject> {
        val museumObjects = mutableListOf<MuseumObject>()
        while (museumObjects.size < n) {
            val objectID = objectIDs.random()
            CoroutineScope(Dispatchers.IO).launch {
                val response = try {
                    service.fetchMuseumObject(objectID)
                } catch(e: IOException) {
                    Log.e(TAG, "IOException, you might not have internet connection")
                    return@launch
                } catch (e: HttpException) {
                    Log.e(TAG, "HttpException, unexpected response")
                    return@launch
                }
                if(response.isSuccessful && response.body() != null) {
                    museumObjects.add(response.body()!!)
                } else {
                    Log.e(TAG, "Response not successful")
                }
            }
        }
        return museumObjects.toList()
    }

    fun getNObjectsByDepto(n: Int, deptoId: Int): List<MuseumObject> {
        val departmentObjects = getObjectIDs(deptoId)
        val museumObjects = mutableListOf<MuseumObject>()
        while (museumObjects.size < n) {
            val objectID = objectIDs.random()
            CoroutineScope(Dispatchers.IO).launch {
                val response = try {
                    service.fetchMuseumObject(objectID)
                } catch(e: IOException) {
                    Log.e(TAG, "IOException, you might not have internet connection")
                    return@launch
                } catch (e: HttpException) {
                    Log.e(TAG, "HttpException, unexpected response")
                    return@launch
                }
                if(response.isSuccessful && response.body() != null) {
                    museumObjects.add(response.body()!!)
                } else {
                    Log.e(TAG, "Response not successful")
                }
            }
        }
        return museumObjects.toList()
    }
}
