<?php

use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\Route;
use App\Models\Intervention;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "api" middleware group. Make something great!
|
*/

Route::post('/save', function (Request $request) {
    $json = $request->post('intervention');
    Intervention::create(['intervention' => $json, 'name' => $request->post('name')]);
    return "saved";
});

Route::get('/', function (Request $request) {
    $interventions = Intervention::all();

    $response = new Response($interventions);
    $response->header('Access-Control-Allow-Origin', '*');
    $response->header('Access-Control-Allow-Methods', '*');
    $response->header('Access-Control-Allow-Credentials', true);
    $response->header('Access-Control-Allow-Headers', 'X-Requested-With,Content-Type,X-Token-Auth,Authorization');
    $response->header('Accept', 'application/json');

    return $response;
});
Route::post('/', function () {
    $interventions = Intervention::all();
    return json_encode($interventions);
});