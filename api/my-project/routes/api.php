<?php

use Illuminate\Http\Request;
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
    return $interventions;
});